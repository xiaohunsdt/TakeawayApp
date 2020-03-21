package net.novaborn.takeaway.order.services;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.DeliveryType;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    AddressService addressService;

    @Test
    public void getOrderCountTodayTest() {
        System.out.println(orderService.getOrderCount(new Date(), DeliveryType.NORMAL));
    }

    @Test
    public void getOrderCountByStateUTest() {
        System.out.println(orderService.getOrderCountByStateU(null, OrderStateEx.WAIT_EAT));
    }

    @Test
    public void getTodayOrderCountByStateUTest() {
        System.out.println(orderService.getTodayOrderCountByStateU(null, OrderStateEx.WAIT_EAT));
    }

    @Test
    public void getOrderListTest() {
        Date start = DateUtil.parseDateTime("2020-03-21 14:20:00");
        Date end = DateUtil.parseDateTime("2020-03-21 15:40:00");
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));
        List<Order> orderList = orderService.getOrderList(args).parallelStream()
                .filter(order -> DateUtil.isIn(order.getCreateDate(), start, end))
                .collect(Collectors.toList());
//        orderList.stream().forEach(order-> System.out.println(order.getUserId()));
        System.out.println(orderList.stream()
                .map(order -> addressService.getById(order.getAddressId()).getPhone())
                .collect(Collectors.joining(",")));
    }
}

