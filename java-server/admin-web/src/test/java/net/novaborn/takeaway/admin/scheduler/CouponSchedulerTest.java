package net.novaborn.takeaway.admin.scheduler;

import net.novaborn.takeaway.admin.service.impl.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CouponSchedulerTest {
    @Autowired
    CouponScheduler couponScheduler;

    @Test
    void couponExpired() {
        couponScheduler.couponExpired();
    }
}