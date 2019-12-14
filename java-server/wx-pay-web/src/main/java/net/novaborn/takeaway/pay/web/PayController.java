package net.novaborn.takeaway.pay.web;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.pay.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.pay.exception.PayExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/wx/pay")
public class PayController extends BaseController {
    private OrderService orderService;

    private WxPayService wxPayService;

    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("createPayInfo")
    @ResponseBody
    public WxPayMpOrderResult createPayInfo(@RequestParam String orderId) throws WxPayException {
        String openId = jwtTokenUtil.getUsernameFromToken(request);

        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setOpenid(openId);
        request.setBody("支付-川香苑外卖");
        request.setOutTradeNo(orderId);
        request.setTotalFee(getOrderPrice(order.get())); // 精确到分
        request.setSpbillCreateIp(this.request.getLocalAddr());
        request.setNotifyUrl("http://pay.cxy.novaborn.net/api/wx/pay/notice");
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

        log.info("订单:{},创建微信支付预信息成功!!", orderId);
        return result;
    }

    @RequestMapping("confirmOrder")
    @ResponseBody
    public SuccessTip confirmOrder(@RequestParam String orderId) {
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

        log.info("订单:{},支付验证成功!!", orderId);
        return new SuccessTip();
    }

    @RequestMapping("notice")
    @ResponseBody
    public String notice(@RequestBody String xmlData) throws Exception {
        WxPayOrderNotifyResult notifyResult;
        try {
            notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
        } catch (WxPayException e) {
            log.error("", e);
            throw e;
        }

        String orderId = notifyResult.getOutTradeNo();
        int totalPrice = notifyResult.getTotalFee();
        String state = notifyResult.getResultCode();
        this.confirmOrder(orderId, totalPrice, state);

        log.info("订单:{},支付回调验证成功!!", orderId);
        return WxPayNotifyResponse.success("成功");
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
        return order.getRealPrice() * 6 / 10;
//        return 1;
    }
}
