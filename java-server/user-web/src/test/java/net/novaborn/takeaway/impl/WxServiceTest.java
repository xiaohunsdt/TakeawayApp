package net.novaborn.takeaway.impl;

import net.novaborn.takeaway.user.UserApplication;
import net.novaborn.takeaway.user.service.impl.WxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
public class WxServiceTest {
    @Autowired
    WxService wxService;

    @Test
    public void setSessionKey() {
        wxService.setSessionKey("asdadzxcxzc","aaaaaaa");
    }

    @Test
    public void getSessionKey() {
        System.out.println(wxService.getSessionKey("asdadzxcxzc"));
    }
}