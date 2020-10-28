package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.config.properties.SystemProperties;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;

import java.util.List;
import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class OrderDetailWrapper extends BaseControllerWrapper {

    public OrderDetailWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SystemProperties systemProperties = SpringContextHolder.getBean(SystemProperties.class);
        AddressService addressService = SpringContextHolder.getBean(AddressService.class);
        OrderItemService orderItemService = SpringContextHolder.getBean(OrderItemService.class);
        StoreService storeService = SpringContextHolder.getBean(StoreService.class);
        SettingService settingService = SpringContextHolder.getBean(SettingService.class);

        Address address = addressService.getById((Long) map.get("addressId"));
        List<OrderItem> orderItemList = orderItemService.selectByOrderId((Long) map.get("id"));
        Store store = storeService.getById((Long) map.get("storeId"));
        Setting storeLogo = settingService.getSettingByName((Long) map.get("storeId"), "store_logo", SettingScope.STORE);
//        Setting storeAddress = settingService.getSettingByName((Long) map.get("storeId"), "store_address", SettingScope.STORE);

        map.put("address", address);
        map.put("orderItemList", new OrderItemWrapper(orderItemList).warp());
        map.put("storeName", store.getName());
        map.put("storeLogo",systemProperties.getUploadServerUrl() + storeLogo.getValue());
        map.put("storeAddress", store.getAddress());
        map.remove("addressId");
    }
}
