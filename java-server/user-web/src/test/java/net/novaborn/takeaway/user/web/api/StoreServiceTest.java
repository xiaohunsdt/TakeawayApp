package net.novaborn.takeaway.user.web.api;

import com.alibaba.fastjson.JSON;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.user.UserApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @Test
    void getAppointmentTimes() {
        System.out.println(JSON.toJSONString(storeService.getAppointmentTimes(111L)));
    }
}