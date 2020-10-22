package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.store.entity.Balance;
import net.novaborn.takeaway.store.service.impl.BalanceService;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class StoreWrapper extends BaseControllerWrapper {

    public StoreWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        BalanceService balanceService = SpringContextHolder.getBean(BalanceService.class);

        Balance balance = balanceService.getById((Long) map.get("id"));
        map.put("balance", balance.getMoney());
    }
}
