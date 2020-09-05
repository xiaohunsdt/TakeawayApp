package net.novaborn.takeaway.store.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.impl.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class StoreTest {
    @Autowired
    StoreService storeService;

    @Test
    public void createTest() {
        Store store = new Store();
        store.setName("川香苑-新村店");
        store.insert();
    }

    @Test
    public void updateTest() {
    }
}