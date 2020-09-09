package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.store.dto.ServiceStateDto;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;

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
        StoreService storeService = SpringContextHolder.getBean(StoreService.class);
        SettingService settingService = SpringContextHolder.getBean(SettingService.class);

        Setting address = settingService.getSettingByName((Long) map.get("id"), "store_address", SettingScope.STORE);
        ServiceStateDto storeState = storeService.getServiceState((Long) map.get("id"));

        map.put("address", address.getValue());
        map.put("serviceState", storeState.getState());
    }
}
