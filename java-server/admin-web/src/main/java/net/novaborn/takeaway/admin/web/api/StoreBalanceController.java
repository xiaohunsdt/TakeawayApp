package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import net.novaborn.takeaway.store.service.impl.BalanceLogService;
import net.novaborn.takeaway.store.service.impl.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/store/balance")
public class StoreBalanceController extends BaseController {
    private BalanceService balanceService;

    private BalanceLogService balanceLogService;
}
