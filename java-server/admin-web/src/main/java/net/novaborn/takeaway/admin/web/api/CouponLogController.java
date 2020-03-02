package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.CouponLogWrapper;
import net.novaborn.takeaway.coupon.service.impl.CouponLogService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/coupon/log")
public class CouponLogController extends BaseController {
    private UserService userService;

    private CouponLogService couponLogService;

    @PostMapping("getCouponLogListByPage")
    public ResponseEntity<Page> getCouponLogListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
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

        page = (Page) couponLogService.getLogListByPage(page, args);
        page.setRecords((List) new CouponLogWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }
}
