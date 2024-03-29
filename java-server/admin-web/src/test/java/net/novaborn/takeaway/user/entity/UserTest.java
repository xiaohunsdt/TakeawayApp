package net.novaborn.takeaway.user.entity;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void createUserTest() {
        User user = new User();
        user.setName("test");
        user.setAvatar("http://www.baidu.com");
        userService.save(user);
    }

    @Test
    public void getUserTest() {
        System.out.println(userService.getById("49268c005a631e2d77b7b90a206fe0eb"));
    }

    @Test
    public void updateUserTest() {
        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();

        User user = new User();
        user.setId(defaultIdentifierGenerator.nextId(user));
        user.setName("test1");
        userService.updateById(user);
    }
}
