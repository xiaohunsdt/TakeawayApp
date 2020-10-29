package net.novaborn.takeaway.admin.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.store.service.impl.BalanceLogService;
import net.novaborn.takeaway.store.service.impl.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/store/balance")
public class StoreBalanceController extends BaseController {
    private BalanceService balanceService;

    private BalanceLogService balanceLogService;

    @PostMapping("log/getListByPage")
    public ResponseEntity getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        args.put("storeId",sysContext.getCurrentStoreId());
        page = (Page) balanceLogService.getListByPage(page, args);
        return ResponseEntity.ok(page);
    }
}
