package net.novaborn.takeaway.pay.services.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.mq.sender.OrderAutoReceiveSender;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.RefundLog;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.enums.RefundState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.order.service.impl.RefundLogService;
import net.novaborn.takeaway.pay.enums.PayExceptionEnum;
import net.novaborn.takeaway.pay.exception.PayServiceException;
import net.novaborn.takeaway.pay.services.IPayService;
import net.novaborn.takeaway.store.service.impl.BalanceLogService;
import net.novaborn.takeaway.store.service.impl.BalanceService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PayService implements IPayService {

    private final String PAY_NOTICE_URL = "https://pay.cxy.novaborn.net/api/wx/pay/notice";

    private final String REFUND_NOTICE_URL = "https://pay.cxy.novaborn.net/api/wx/pay/refund/notice";

    private OrderService orderService;

    private RefundLogService refundLogService;

    private SettingService settingService;

    private BalanceService balanceService;

    private BalanceLogService balanceLogService;

    private WxPayService wxPayService;

    private OrderAutoReceiveSender orderAutoReceiveSender;

    @Override
    public WxPayMpOrderResult createPayInfo(String openId, Long orderId, String ipAddr) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setOpenid(openId);
        request.setBody("支付-聚韩外卖");
        request.setOutTradeNo(orderId.toString());
        // 精确到分
        request.setTotalFee(getOrderPrice(order.get()));
        request.setSpbillCreateIp(ipAddr);
        request.setNotifyUrl(PAY_NOTICE_URL);
        request.setTradeType("JSAPI");

        WxPayMpOrderResult result;
        try {
            result = wxPayService.createOrder(request);
        } catch (WxPayException e) {
            SysException sysException = new SysException(PayExceptionEnum.PAY_CREATE_ERROR);
            sysException.setMessage(e.getErrCodeDes());
            throw sysException;
        }

//        orderPayStatusSender.send(order.get(), new Date(), 15);
        log.info("订单:{},创建微信支付预信息成功!!", orderId);
        return result;
    }

    @Override
    public void confirmPay(Long orderId) {
        WxPayOrderQueryResult result;
        try {
            result = wxPayService.queryOrder(null, orderId.toString());
        } catch (WxPayException e) {
            PayServiceException payServiceException = new PayServiceException(PayExceptionEnum.QUERY_PAY_ERROR);
            payServiceException.setMessage(e.getErrCodeDes());
            throw payServiceException;
        }

        int totalPrice = result.getTotalFee();
        String state = result.getTradeState();

        if (this.confirmPay(orderId, totalPrice, state)) {
            Order order = orderService.getById(orderId);

            // 设置店铺资金和记录
            long money = order.getRealPrice().longValue();
            long afterMoney = balanceService.add(order.getStoreId(), money);
            balanceLogService.setMoneyLog(order.getStoreId(), money, afterMoney, 1, order.getId(), order.getNumber(), money);

            // 系统是否允许自动接单
            Setting orderAutoReceive = settingService.getSettingByName(order.getStoreId(), "auto_receive_order", SettingScope.SYSTEM);
            if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
                orderAutoReceiveSender.send(order);
            }
        }
    }

    @Override
    public boolean confirmPay(Long orderId, int totalPrice, String state) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new PayServiceException(OrderExceptionEnum.ORDER_NOT_EXIST));

        // 确认订单已经支付并且支付
        if ("SUCCESS".equals(state)) {
            if (totalPrice == getOrderPrice(order.get())) {
                if (order.get().getPayState() != PayState.PAID) {
                    order.get().setPayState(PayState.PAID);
                    order.get().setOrderState(OrderState.WAITING_RECEIVE);
                    orderService.updateById(order.get());

                    return true;
                } else {
                    log.warn("订单ID: {}, {}", orderId, PayExceptionEnum.PAY_PAID_ERROR.getMessage());
                }
            } else {
                throw new PayServiceException(PayExceptionEnum.PAY_PRICE_ERROR);
            }
        } else {
            throw new PayServiceException(PayExceptionEnum.PAY_ERROR, state);
        }
        return false;
    }

    @Override
    public Tip refundPay(RefundLog refundLog) {
        WxPayRefundRequest request = new WxPayRefundRequest();
        request.setOutTradeNo(refundLog.getOrderId().toString());
        request.setOutRefundNo(refundLog.getId().toString());
//        request.setOutRefundNo(String.format("R_%d_%d", refundLogService.getRefundLogCountByOrderId(refundLog.getOrderId()), refundLog.getOrderId()));
        request.setTotalFee(getWonToRmb(refundLog.getAllPrice()));
        request.setRefundFee(getWonToRmb(refundLog.getRefundMoney()));
        request.setNotifyUrl(REFUND_NOTICE_URL);
        try {
            wxPayService.refund(request);
        } catch (WxPayException e) {
            return new ErrorTip(-1, e.getCustomErrorMsg());
        }

        return new SuccessTip();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirmRefund(Long refundId, int refundPrice, String state) {
        Optional<RefundLog> refundLog = Optional.ofNullable(refundLogService.getById(refundId));
        refundLog.orElseThrow(() -> new PayServiceException(PayExceptionEnum.REFUND_LOG_NOT_EXIST));

        // 确认订单已经支付并且支付
        if ("SUCCESS".equals(state)) {
            if (getWonToRmb(refundLog.get().getRefundMoney()) == refundPrice) {
                if (refundLog.get().getState() == RefundState.PROCESSING) {
                    refundLog.get().setState(RefundState.DONE);
                    refundLogService.updateById(refundLog.get());

                    // 设置店铺资金和记录
                    long money = refundLog.get().getRefundMoney();
                    long afterMoney = balanceService.sub(refundLog.get().getStoreId(), money);
                    balanceLogService.setMoneyLog(refundLog.get().getStoreId(), money, afterMoney, 5, refundLog.get().getId(), money);
                } else {
                    log.warn("退款ID: {}, {}", refundId, PayExceptionEnum.REFUND_HAD_DONE_ERROR.getMessage());
                }
            } else {
                throw new PayServiceException(PayExceptionEnum.REFUND_PRICE_ERROR);
            }
        } else {
            throw new PayServiceException(PayExceptionEnum.REFUND_ERROR, state);
        }
    }

    /**
     * 计算微信金额公式，精确到分
     *
     * @param order
     * @return
     */
    private int getOrderPrice(Order order) {
        return getWonToRmb(order.getRealPrice());
//        return 1;
    }

    private int getWonToRmb(int kWon) {
        return kWon * 6 / 10;
    }
}
