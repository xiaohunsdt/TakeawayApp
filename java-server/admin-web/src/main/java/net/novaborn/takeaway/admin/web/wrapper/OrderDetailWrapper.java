package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderDetailService;
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
        OrderDetailService orderDetailService = SpringContextHolder.getBean(OrderDetailService.class);
        OrderItemService orderItemService = SpringContextHolder.getBean(OrderItemService.class);

        Address address = addressService.getById((Long) map.get("addressId"));
        OrderDetail orderDetail = orderDetailService.getById((Long) map.get("id"));
        List<OrderItem> orderItemList = orderItemService.selectByOrderId((Long) map.get("id"));

        map.put("address", address);
        map.put("orderDetail", orderDetail);
        map.put("orderItemList", orderItemList);

        map.remove("addressId");
    }
}
