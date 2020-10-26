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
import net.novaborn.takeaway.store.entity.BalanceLog;
import net.novaborn.takeaway.store.service.impl.BalanceLogService;
import net.novaborn.takeaway.store.service.impl.BalanceService;
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
@RequestMapping("/api/admin/balance")
public class BalanceController extends BaseController {

    private BalanceLogService balanceLogService;

    @PostMapping("log/getListByPage")
    public ResponseEntity getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) balanceLogService.getListByPage(page, args);
//        page.setRecords((List) new DeliveryWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }
}
