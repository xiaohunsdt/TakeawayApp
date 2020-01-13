package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;

import java.util.List;
import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class OrderDetailWrapper extends OrderWrapper {

    public OrderDetailWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        AddressService addressService = SpringContextHolder.getBean(AddressService.class);
        OrderItemService orderItemService = SpringContextHolder.getBean(OrderItemService.class);

        Address address = addressService.getById((String) map.get("addressId"));
        List<OrderItem> orderItemList = orderItemService.selectByOrderId((String) map.get("id"));

        map.put("address", address);
        map.put("orderItemList", new OrderItemWrapper(orderItemList).warp());

        map.remove("addressId");
    }
}
