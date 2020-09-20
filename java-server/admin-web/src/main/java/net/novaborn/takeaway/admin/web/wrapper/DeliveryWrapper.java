package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.service.impl.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * 商品包装类
 *
 * @author xiaohun
 */
public class DeliveryWrapper extends BaseControllerWrapper {

    public DeliveryWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        OrderService orderService = SpringContextHolder.getBean(OrderService.class);
        Order order = orderService.getById((Long) map.get("orderId"));

        if (map.get("finishDate") != null) {
            map.put("orderFinishMinute", DateUtil.between((Date) map.get("orderCreateDate"), (Date) map.get("finishDate"), DateUnit.MINUTE));
            map.put("deliveryFinishMinute", DateUtil.between((Date) map.get("createDate"), (Date) map.get("finishDate"), DateUnit.MINUTE));
            map.put("finishDate", DateUtil.format((Date) map.get("finishDate"), "MM-dd HH:mm"));
        } else {
            map.put("orderFinishMinute", "未完成");
            map.put("deliveryFinishMinute", "未完成");
        }

        map.put("number", order.getNumber());
        map.put("orderCreateDate", DateUtil.format((Date) map.get("orderCreateDate"), "MM-dd HH:mm"));
        map.put("createDate", DateUtil.format((Date) map.get("createDate"), "MM-dd HH:mm"));
    }
}
