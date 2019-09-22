package net.novaborn.takeaway.admin.entity;

import net.novaborn.takeaway.admin.service.impl.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Autowired
    AdminService adminService;

    @Test
    public void createAdminTest() {
        Admin admin = new Admin();
        admin.setUserName("admin");
        admin.setPassword("admin");
        admin.insert();
    }

    @Test
    public void updateAdminTest() {
        Admin admin = adminService.getById("8b99e2780bf8c3c43d8f95bf9e2492a0");
        admin.setPassword("admin");
        admin.updateById();
    }

    @Test
    public void selectByNameTest() {
        Optional<Admin> admin = adminService.getBaseMapper().selectByName("admin");
        admin.ifPresent(val -> System.out.println(val));
    }
}