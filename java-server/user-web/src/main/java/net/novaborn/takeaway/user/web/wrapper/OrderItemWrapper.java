package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
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
public class OrderItemWrapper extends OrderWrapper {

    public OrderItemWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SystemProperties systemProperties = SpringContextHolder.getBean(SystemProperties.class);

        if (map.get("goodsThumb") != null){
            map.put("goodsThumb", systemProperties.getUploadServerUrl() + map.get("goodsThumb"));
        }
    }
}
