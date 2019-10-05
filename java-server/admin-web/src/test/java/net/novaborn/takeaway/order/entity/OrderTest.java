package net.novaborn.takeaway.order.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.service.impl.UserService;
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

    @Test
    public void createUserTest() {

    }

    @Test
    public void getUserTest() {

    }

    @Test
    public void updateUserTest() {

    }
}
