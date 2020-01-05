package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.CommentWrapper;
import net.novaborn.takeaway.admin.web.wrapper.CouponTemplateWrapper;
import net.novaborn.takeaway.admin.web.wrapper.CouponWrapper;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.service.impl.CouponService;
import net.novaborn.takeaway.order.service.impl.CommentService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/coupon")
public class CouponController extends BaseController {
    private UserService userService;
    private CouponService couponService;

    @GetMapping("getCouponById")
    public ResponseEntity getCouponById(String id) {
        Coupon coupon = couponService.getById(id);
        return ResponseEntity.ok(new CouponWrapper(coupon).warp());
    }

    @PostMapping("getCouponListByPage")
    public ResponseEntity<Page> getCouponListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        // 根据昵称获取订单
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<String> ids = userService.getByNickName((String) args.get("nickName")).stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            if (ids.size() > 0) {
                args.put("userIds", ids);
            } else {
                args.put("userIds", Arrays.asList("-1"));
            }
        }

        page = (Page) couponService.getCouponListByPage(page, args);
        page.setRecords((List)new CouponWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("createCoupon")
    public Tip createCoupon(String templateId, String userId, Integer count) {
        return null;
    }

    @ResponseBody
    @PostMapping("deleteCoupon")
    public Tip deleteCoupon(String id) {
        if (couponService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
