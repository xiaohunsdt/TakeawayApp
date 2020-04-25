package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.CouponWrapper;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.exception.CouponTemplateExceptionEnum;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.coupon.services.impl.CouponTemplateService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    private CouponTemplateService couponTemplateService;

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
        page.setRecords((List) new CouponWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("generateCoupon")
    public Tip generateCoupon(String templateId, String userIds, Integer expireDays, Integer count) {
        Optional<CouponTemplate> couponTemplate = Optional.ofNullable(couponTemplateService.getById(templateId));
        couponTemplate.orElseThrow(() -> new SysException(CouponTemplateExceptionEnum.HAVE_NO_THIS_TEMPLATE));

        List<String> userIdArray = new ArrayList<>(List.of(userIds.replaceAll("\r", "").split("\n")));
        userIdArray.removeAll(Arrays.asList("", null));
        if (userIdArray.size() > 0) {
            couponService.generateCoupon(couponTemplate.get(), userIdArray, expireDays, count);
        } else {
            couponService.generateCoupon(couponTemplate.get(), expireDays, count);
        }
        return new SuccessTip();
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
