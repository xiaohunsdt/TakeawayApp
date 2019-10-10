package net.novaborn.takeaway.order.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class OrderItemTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Test
    public void createOrderItemTest() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId("f4b4e5d5dbc88094db45ca98f3e81eec");
        orderItem.setGoodsId("2d912f7f177635c9cb0e6a28e5e4439e");
        orderItem.setGoodsName("松花蛋豆腐");
        orderItem.setGoodsPrice(6000);
        orderItem.setGoodsCount(3);
        orderItem.insert();
    }

    @Test
    public void getOrderTest() {
        System.out.println(orderService.getById("030139ac7dd581c329c8578d9060a43e"));
    }
}
