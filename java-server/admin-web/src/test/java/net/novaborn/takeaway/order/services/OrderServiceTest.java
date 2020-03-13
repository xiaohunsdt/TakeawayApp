package net.novaborn.takeaway.order.services;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.enums.DeliveryType;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

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
}

