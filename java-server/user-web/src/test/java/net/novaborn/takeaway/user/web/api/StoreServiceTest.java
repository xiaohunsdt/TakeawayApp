package net.novaborn.takeaway.user.web.api;

import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.user.UserApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
class StoreServiceTest {
    @Autowired
    StoreService storeService;
}