package net.novaborn.takeaway.order.entity;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class OrderTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Test
    public void createOrderTest() {
        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();

        Order order = new Order();
        order.setNumber(2);
        order.setUserId(defaultIdentifierGenerator.nextId(order));
        order.setAddressId(defaultIdentifierGenerator.nextId(order));
        order.setPaymentWay(PaymentWay.BALANCE);
        order.setAllPrice(1000);
        order.setRealPrice(1000);
        order.insert();
        System.out.println(order);
    }

    @Test
    public void getOrderTest() {
        System.out.println(orderService.getById("0b10b2674464e7fe847419a098510844"));
    }

    @Test
    public void updateOrderTest() {
        Order order = orderService.getById("030139ac7dd581c329c8578d9060a43e");
        order.setPaymentWay(PaymentWay.ALI_PAY);
        order.updateById();
    }
}
