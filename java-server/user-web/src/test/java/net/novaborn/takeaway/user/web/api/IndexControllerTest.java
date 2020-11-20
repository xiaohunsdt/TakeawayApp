package net.novaborn.takeaway.user.web.api;

import com.alibaba.fastjson.JSON;
import net.novaborn.takeaway.order.enums.OrderType;
import net.novaborn.takeaway.user.UserApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
class IndexControllerTest {
    @Autowired
    IndexController indexController;

    @Test
    void getAppointmentTimes() {
        System.out.println(JSON.toJSONString(indexController.getAppointmentTimes(OrderType.APPOINTMENT)));
    }
}