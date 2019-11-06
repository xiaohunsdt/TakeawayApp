package net.novaborn.takeaway.user.web.wx;

import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.web.api.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/user/wx/pay")
public class PayController extends BaseController {
    private OrderService orderService;

    private WxPayService wxPayService;

    @GetMapping("createPayInfo")
    @ResponseBody
    public WxPayAppOrderResult createPayInfo(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        request.setBody("支付-川香苑外卖");
        request.setOutTradeNo(order.get().getId());
        request.setTotalFee(order.get().getRealPrice() * 100);
        request.setSpbillCreateIp(this.request.getLocalAddr());
        request.setNotifyUrl("http://cxy.novaborn.net/api/user/wx/pay/notice");
        request.setTradeType("JSAPI");

        WxPayAppOrderResult result;
        try {
            result = wxPayService.createOrder(request);
        } catch (WxPayException e) {
            log.error("", e);
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }

        return result;
    }
}
