package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.common.entity.SysContext;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.impl.StoreService;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class WithdrawWrapper extends BaseControllerWrapper {

    public WithdrawWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SysContext sysContext = SpringContextHolder.getBean(SysContext.class);

        if (sysContext.getCurrentStoreId() == 0L) {
            StoreService storeService = SpringContextHolder.getBean(StoreService.class);
            Store store = storeService.getById((Long) map.get("storeId"));
            map.put("storeName", store.getName());
        }
    }
}
