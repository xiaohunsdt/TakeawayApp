package net.novaborn.takeaway.impl;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.user.UserApplication;
import net.novaborn.takeaway.user.mq.CouponExpiredSender;
import net.novaborn.takeaway.user.mq.OrderPayExpiredSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
public class RabbitMQTest {
    @Autowired
    OrderPayExpiredSender orderPayExpiredSender;

    @Autowired
    CouponExpiredSender couponExpiredSender;

    @Test
    public void orderPayExpiredSender() {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        orderPayExpiredSender.send(order, 10);
        System.out.println(DateUtil.formatDateTime(new Date()));
    }

    @Test
    public void couponExpiredSender() {
        Coupon coupon = new Coupon();
        coupon.setId(UUID.randomUUID().toString());
        couponExpiredSender.send(coupon, 10);
        System.out.println(DateUtil.formatDateTime(new Date()));
    }
}