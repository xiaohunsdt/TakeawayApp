package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.admin.web.wrapper.DeliveryWrapper;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.impl.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/delivery")
public class DeliveryController extends BaseController {
    private AdminService adminService;

    private DeliveryService deliveryService;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("getDeliveryListByPage")
    public ResponseEntity getDeliveryListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        if (StrUtil.isNotBlank((String) args.get("adminName"))) {
            Optional<Admin> admin = adminService.getByAdminName((String) args.get("adminName"));
            admin.ifPresentOrElse((item) -> args.put("adminId", item.getId()), () -> args.put("adminId", ""));
        }

        if (StrUtil.isNotBlank((String) args.get("paymentWay"))) {
            args.put("paymentWay", PaymentWay.valueOf((String) args.get("paymentWay")));
        } else {
            args.remove("paymentWay");
        }

        page = (Page) deliveryService.getDeliveryListByPage(page, args);
        page.setRecords((List) new DeliveryWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @PostMapping("getMyDeliveryListByPage")
    public ResponseEntity getMyDeliveryListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);
        args.put("adminId", Long.parseLong(adminId));

        page = (Page) deliveryService.getDeliveryListByPage(page, args);
        page.setRecords((List) new DeliveryWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }
}
