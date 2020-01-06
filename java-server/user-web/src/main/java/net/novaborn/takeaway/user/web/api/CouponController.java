package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.service.impl.CouponService;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.wrapper.CouponWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private CouponService couponService;

    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("getCouponListU")
    public ResponseEntity getCouponListU() {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);

        List<Coupon> couponList = couponService.getCouponListByUserId(user.get().getId(), true);
        return ResponseEntity.ok(new CouponWrapper(couponList).warp());
    }
}
