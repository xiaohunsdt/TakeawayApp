package net.novaborn.takeaway.pay.web;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.pay.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.pay.mq.OrderPayStatusSender;
import net.novaborn.takeaway.pay.services.impl.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/wx/pay")
public class PayController extends BaseController {

    private PayService payService;

    private OrderPayStatusSender orderPayStatusSender;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("createPayInfo")
    @ResponseBody
    public WxPayMpOrderResult createPayInfo(@RequestParam Long orderId) throws WxPayException {
        String openId = jwtTokenUtil.getUsernameFromToken(request);

        WxPayMpOrderResult result = payService.createPayInfo(openId, orderId, this.request.getLocalAddr());
        log.info("订单:{},创建微信支付预信息成功!!", orderId);

        orderPayStatusSender.send(orderId, 8);
        return result;
    }

//    @PostMapping("closeOrder")
//    @ResponseBody
//    public WxPayMpOrderResult closeOrder(@RequestParam Long orderId) throws WxPayException {
//        String openId = jwtTokenUtil.getUsernameFromToken(request);
//
//        WxPayMpOrderResult result = payService.createPayInfo(openId, orderId, this.request.getLocalAddr());
//        log.info("订单:{},创建微信支付预信息成功!!", orderId);
//
//        orderPayStatusSender.send(orderId, 30);
//        return result;
//    }

    @RequestMapping("confirmOrder")
    @ResponseBody
    public SuccessTip confirmOrder(@RequestParam Long orderId) {
        payService.confirmPay(orderId);

        log.info("订单:{},支付验证成功!!", orderId);
        return new SuccessTip();
    }

    @RequestMapping("notice")
    @ResponseBody
    public String notice(@RequestBody String xmlData) throws Exception {
//        WxPayOrderNotifyResult notifyResult;
//        try {
//            notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
//        } catch (WxPayException e) {
//            log.error("支付回调验证失败!!", e);
//            throw e;
//        }
//
//        String orderId = notifyResult.getOutTradeNo();
//        int totalPrice = notifyResult.getTotalFee();
//        String state = notifyResult.getResultCode();
//        this.confirmOrder(orderId, totalPrice, state);

        log.info("回调信息:{},支付回调验证成功!!", xmlData);
        return WxPayNotifyResponse.success("成功");
    }
}
