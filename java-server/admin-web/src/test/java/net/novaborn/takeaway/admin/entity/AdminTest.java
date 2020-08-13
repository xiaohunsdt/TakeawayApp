package net.novaborn.takeaway.admin.entity;

import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
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
        admin.setUserName("test1aaa");
        admin.setPassword("123456");
        admin.insert();
    }

    @Test
    public void updateAdminTest() {
        Admin admin = adminService.getById(1293623861681692671L);
        System.out.println(admin);
    }

    @Test
    public void selectByNameTest() {
        Optional<Admin> admin = adminService.getBaseMapper().selectByName("admin1");
        admin.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));
    }
}