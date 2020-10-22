package net.novaborn.takeaway.pay.services.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.mq.sender.OrderAutoReceiveSender;
import net.novaborn.takeaway.mq.sender.OrderPayStatusSender;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.pay.exception.PayExceptionEnum;
import net.novaborn.takeaway.pay.services.IPayService;
import net.novaborn.takeaway.store.service.impl.BalanceLogService;
import net.novaborn.takeaway.store.service.impl.BalanceService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PayService implements IPayService {
    private final String NOTICE_URL = "http://pay.cxy.novaborn.net/api/wx/pay/notice";

    private OrderService orderService;

    private BalanceService balanceService;

    private BalanceLogService balanceLogService;

    private SettingService settingService;

    private OrderAutoReceiveSender orderAutoReceiveSender;

    private OrderPayStatusSender orderPayStatusSender;

    private WxPayService wxPayService;

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
        request.setNotifyUrl(NOTICE_URL);
        request.setTradeType("JSAPI");

        WxPayMpOrderResult result;
        try {
            result = wxPayService.createOrder(request);
        } catch (WxPayException e) {
            log.error(null, e);
            SysException sysException = new SysException(PayExceptionEnum.PAY_CREATE_ERROR);
            sysException.setMessage(e.getErrCodeDes());
            throw sysException;
        }

        orderPayStatusSender.send(order.get(), new Date(), 15);
        log.info("订单:{},创建微信支付预信息成功!!", orderId);
        return result;
    }

    @Override
    public void confirmPay(Long orderId) {
        WxPayOrderQueryResult result;
        try {
            result = wxPayService.queryOrder(null, orderId.toString());
        } catch (WxPayException e) {
            log.error(null, e);
            SysException sysException = new SysException(PayExceptionEnum.QUERY_PAY_ERROR);
            sysException.setMessage(e.getErrCodeDes());
            throw sysException;
        }

        int totalPrice = result.getTotalFee();
        String state = result.getTradeState();
        this.confirmOrder(orderId, totalPrice, state);
    }

    private void confirmOrder(Long orderId, int totalPrice, String state) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        // 确认订单已经支付并且支付
        if ("SUCCESS".equals(state)) {
            if (totalPrice == getOrderPrice(order.get())) {
                if (order.get().getPayState() != PayState.PAID) {
                    order.get().setPayState(PayState.PAID);
                    order.get().setOrderState(OrderState.WAITING_RECEIVE);
                    order.get().updateById();
                } else {
                    throw new SysException(PayExceptionEnum.PAY_PAID_ERROR);
                }
            } else {
                throw new SysException(PayExceptionEnum.PAY_PRICE_ERROR);
            }
        } else {
            throw new SysException(PayExceptionEnum.PAY_ERROR, state);
        }

        // 设置店铺资金和记录
        long money = order.get().getRealPrice().longValue();
        long afterMoney = balanceService.add(order.get().getStoreId(), money);
        balanceLogService.setMoneyLog(order.get().getStoreId(), money, afterMoney, 1, order.get().getId(), money);

        // 系统是否允许自动接单
        Setting orderAutoReceive = settingService.getSettingByName(order.get().getStoreId(), "auto_receive_order", SettingScope.SYSTEM);
        if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
            orderAutoReceiveSender.send(order.get());
        }
    }

    /**
     * 计算微信金额公式，精确到分
     *
     * @param order
     * @return
     */
    private int getOrderPrice(Order order) {
        return order.getRealPrice() * 6 / 10;
//        return 1;
    }
}
