package net.novaborn.takeaway.task;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.admin.task.WechatAutoTask;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class WechatAutoTaskTest {
    @Autowired
    OrderService orderService;

    @Autowired
    WechatAutoTask wechatAutoTask;

    @Test
    public void goodsShowTest() {
        wechatAutoTask.goodsShow();
    }

    @Test
    public void orderShowTest() {
        Order order = orderService.getById("933cf62f68042a9291f18a94c09041c9");
        wechatAutoTask.orderShow(order);
    }

    @Test
    public void appointmentShowTest() {
        wechatAutoTask.appointmentShow();
    }
}
