package net.novaborn.takeaway.pay.web;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.pay.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.pay.exception.PayExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        request.setTotalFee(order.get().getRealPrice() * 6 / 10); // 精确到分
        request.setSpbillCreateIp(this.request.getLocalAddr());
        request.setNotifyUrl("http://pay.novaborn.net/api/wx/pay/notice");
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

    @PostMapping("notice")
    @ResponseBody
    public String notice() {
        System.out.println("收到消息了");
        return null;
    }
}
