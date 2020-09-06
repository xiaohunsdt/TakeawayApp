package net.novaborn.takeaway.store.services;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.store.service.impl.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class StoreServiceTest {
    @Autowired
    StoreService storeService;

    @Test
    public void test1() {
        System.out.println(storeService.getAllAvailableStore());
    }
}
