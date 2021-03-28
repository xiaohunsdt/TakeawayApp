package net.novaborn.takeaway.pay.web;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.mq.sender.OrderAutoReceiveSender;
import net.novaborn.takeaway.order.entity.RefundLog;
import net.novaborn.takeaway.order.enums.RefundState;
import net.novaborn.takeaway.order.service.impl.RefundLogService;
import net.novaborn.takeaway.pay.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.pay.exception.PayServiceException;
import net.novaborn.takeaway.pay.services.impl.PayService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
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
    private WxPayService wxPayService;

    private PayService payService;

    private RefundLogService refundLogService;

    private SettingService settingService;

    private OrderAutoReceiveSender orderAutoReceiveSender;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("createPayInfo")
    @ResponseBody
    public WxPayMpOrderResult createPayInfo(@RequestParam Long orderId) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        return payService.createPayInfo(openId, orderId, this.request.getLocalAddr());
    }

    @RequestMapping("confirmOrder")
    @ResponseBody
    public SuccessTip confirmOrder(@RequestParam Long orderId) {
        boolean result = payService.confirmPay(orderId);
        if (result) {
            log.info("订单: {}, 支付验证成功!!", orderId);

            // 系统是否允许自动接单
            Setting orderAutoReceive = settingService.getSettingByName("auto_receive_order", SettingScope.SYSTEM);
            if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
                orderAutoReceiveSender.send(orderId);
            }
        }
        return new SuccessTip();
    }

//    @RequestMapping("refundOrder")
//    @ResponseBody
//    public SuccessTip refundOrder(@RequestParam Long orderId, Integer money) {
//        payService.refundPay(orderId.toString(), money);
//
//        log.info("订单:{},退款成功!!", orderId);
//        return new SuccessTip();
//    }

    @RequestMapping("notice")
    @ResponseBody
    public String noticePay(@RequestBody String xmlData) throws WxPayException {
        WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);

        Long orderId = Long.parseLong(notifyResult.getOutTradeNo());
        int totalPrice = notifyResult.getTotalFee();
        String state = notifyResult.getResultCode();

        try {
            boolean result = payService.confirmPay(orderId, totalPrice, state);
            if (result) {
                log.info("订单: {}, 支付验证成功!!", orderId);

                // 系统是否允许自动接单
                Setting orderAutoReceive = settingService.getSettingByName("auto_receive_order", SettingScope.SYSTEM);
                if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
                    orderAutoReceiveSender.send(orderId);
                }
            }

            log.debug("回调信息: {}", xmlData);
            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            log.error("", e);
            return WxPayNotifyResponse.fail("失败");
        }
    }

    @RequestMapping("refund/notice")
    @ResponseBody
    public String noticeRefund(@RequestBody String xmlData) throws WxPayException {
        WxPayRefundNotifyResult notifyResult = wxPayService.parseRefundNotifyResult(xmlData);
        WxPayRefundNotifyResult.ReqInfo reqInfo = notifyResult.getReqInfo();

        Long refundId = Long.parseLong(reqInfo.getOutRefundNo());
        int refundFee = reqInfo.getRefundFee();
        String state = reqInfo.getRefundStatus();

        try {
            payService.confirmRefund(refundId, refundFee, state);

            log.debug("回调信息:{},退款回调验证成功!!", xmlData);
            return WxPayNotifyResponse.success("成功");
        } catch (Exception e) {
            if (e instanceof PayServiceException) {
                RefundLog refundLog = refundLogService.getById(refundId);
                if (refundLog.getState() == RefundState.PROCESSING) {
                    refundLog.setState(RefundState.FAILED);
                    refundLog.setRejectMsg(state);
                    refundLogService.updateById(refundLog);
                }
            }

            log.error("", e);
            return WxPayNotifyResponse.fail("失败");
        }
    }
}
