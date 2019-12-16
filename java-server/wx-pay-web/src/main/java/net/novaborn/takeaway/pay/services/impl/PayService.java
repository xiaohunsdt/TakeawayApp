package net.novaborn.takeaway.pay.services.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.pay.exception.PayExceptionEnum;
import net.novaborn.takeaway.pay.services.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PayService implements IPayService {
    private OrderService orderService;

    private WxPayService wxPayService;

    private final String NOTICE_URL = "http://pay.cxy.novaborn.net/api/wx/pay/notice";

    @Override
    public WxPayMpOrderResult createPayInfo(String openId, String orderId, String ipAddr) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setOpenid(openId);
        request.setBody("支付-川香苑外卖");
        request.setOutTradeNo(orderId);
        request.setTotalFee(getOrderPrice(order.get())); // 精确到分
        request.setSpbillCreateIp(ipAddr);
        request.setNotifyUrl(NOTICE_URL);
        request.setTradeType("JSAPI");

        WxPayMpOrderResult result;
        try {
            result = wxPayService.createOrder(request);
        } catch (WxPayException e) {
            log.error("", e);
            SysException sysException = new SysException(PayExceptionEnum.PAY_CREATE_ERROR);
            sysException.setMessage(e.getErrCodeDes());
            throw sysException;
        }

        return result;
    }

    @Override
    public void confirmPay(String orderId) {
        WxPayOrderQueryResult result;
        try {
            result = wxPayService.queryOrder(null, orderId);
        } catch (WxPayException e) {
            log.error("", e);
            SysException sysException = new SysException(PayExceptionEnum.QUERY_PAY_ERROR);
            sysException.setMessage(e.getErrCodeDes());
            throw sysException;
        }

        int totalPrice = result.getTotalFee();
        String state = result.getTradeState();
        this.confirmOrder(orderId, totalPrice, state);
    }

    private void confirmOrder(String orderId, int totalPrice, String state) {
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
            throw new SysException(PayExceptionEnum.PAY_ERROR);
        }
    }

    /**
     * 计算微信金额公式，精确到分
     *
     * @param order
     * @return
     */
    private int getOrderPrice(Order order) {
//        return order.getRealPrice() * 6 / 10;
        return 1;
    }
}
