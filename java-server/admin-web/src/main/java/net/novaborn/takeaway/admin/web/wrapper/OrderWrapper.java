package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.enums.OrderType;
import net.novaborn.takeaway.order.service.impl.OrderDetailService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;

import java.util.Date;
import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class OrderWrapper extends BaseControllerWrapper {

    public OrderWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        OrderDetailService orderDetailService = SpringContextHolder.getBean(OrderDetailService.class);
        UserService userService = SpringContextHolder.getBean(UserService.class);
        User user = userService.getById((Long) map.get("userId"));

        if (user.getOpenId() != null) {
            map.put("userName", user.getNickName());
        } else {
            map.put("userName", user.getName());
        }

        OrderType orderType = ((OrderType) map.get("orderType"));
        if (orderType == OrderType.APPOINTMENT || orderType == OrderType.SELF) {
            OrderDetail orderDetail = orderDetailService.getById((Long) map.get("id"));
            map.put("appointmentDate", DateUtil.format(orderDetail.getAppointmentDate(), "MM-dd HH:mm"));
        }

        map.put("createDate", DateUtil.format((Date) map.get("createDate"), "MM-dd HH:mm"));
    }
}
