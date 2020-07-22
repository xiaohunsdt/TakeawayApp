package net.novaborn.takeaway.common.activies.signin;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.impl.WeeklyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class WeeklyTest {
    @Autowired
    private WeeklyService weeklyUtil;

    @Test
    public void checkTest() {
        System.out.println(weeklyUtil.checkSignIn("aaaa"));
    }

    @Test
    public void getTest() {
        System.out.println(weeklyUtil.getSignIn("aaaa"));
    }

    @Test
    public void saveTest() {
        SignIn signIn = new SignIn();
        signIn.setCount(1);
        signIn.setIsExchanged(false);
        weeklyUtil.saveSignIn("aaaa",signIn);
    }
}
