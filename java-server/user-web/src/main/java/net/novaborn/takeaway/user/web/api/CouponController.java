package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import net.novaborn.takeaway.coupon.services.impl.CouponLogService;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.dto.OrderDto;
import net.novaborn.takeaway.user.web.wrapper.CouponLogWrapper;
import net.novaborn.takeaway.user.web.wrapper.CouponWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/coupon")
public class CouponController extends BaseController {
    private UserService userService;

    private OrderService orderService;

    private CouponService couponService;

    private CouponLogService couponLogService;

    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("getCouponListU")
    public ResponseEntity getCouponListU() {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);

        List<Coupon> couponList = couponService.getCouponListByUserId(user.get().getId(), true);
        return ResponseEntity.ok(new CouponWrapper(couponList).warp());
    }

    @ResponseBody
    @PostMapping("getCouponLogByOrderId")
    public Object getCouponLogByOrderId(@RequestParam String orderId) {
        List<CouponLog> couponLogList = couponLogService.getLogListByOrderId(orderId);
        return new CouponLogWrapper(couponLogList).warp();
    }

    @ResponseBody
    @PostMapping("exchangeCoupon")
    public Tip exchangeCoupon(@RequestParam String couponId) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);

        if (couponService.bindCoupon(user.get().getId(), couponId.trim())) {
            return new SuccessTip();
        } else {
            return new ErrorTip(-1, "兑换失败!请联系客服!");
        }
    }

    @ResponseBody
    @PostMapping("checkCouponDiscountPrice")
    public Tip checkCouponDiscountPrice(@RequestBody OrderDto orderDto) {
        Order order = orderDto.getOrder();
        orderService.checkOrder(order, orderDto.getOrderItems());
        order = couponService.getDiscountMoney(order, orderDto.getOrderItems(), orderDto.getCouponId());
        return new SuccessTip(String.valueOf(order.getDiscountedPrices()));
    }
}
