package net.novaborn.takeaway.common.activies.signin;

import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.impl.SignInService;
import net.novaborn.takeaway.admin.AdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class SignInServiceTest {
    @Autowired
    private SignInService signInService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void getTest() {
        Set<String> keys = redisTemplate.keys("signin:*");
        keys.forEach(item -> {
            SignIn signIn = (SignIn) redisTemplate.boundValueOps(item).get();
            signIn.setStoreId(1302193963869949953L);
            signInService.saveSignIn(1302193963869949953L, signIn.getUserId(), signIn.getCreateDate(), signIn);
        });
    }
}
