package net.novaborn.takeaway.admin.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.web.wrapper.DeliveryWrapper;
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

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/delivery")
public class DeliveryController extends BaseController {
    private DeliveryService deliveryService;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("getDeliveryListByPage")
    public ResponseEntity getDeliveryListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
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
