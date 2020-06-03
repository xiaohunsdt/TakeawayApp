package net.novaborn.takeaway.mq;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.mq.dto.AutoMessage;
import net.novaborn.takeaway.mq.sender.WechatAutoSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class WechatAutoTest {
    @Autowired
    private WechatAutoSender wechatAutoSender;

    @Test
    public void sendTest() {
        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage("asdadad");
        autoMessage.setImgUrlList(
            Arrays.asList("https://admin.cxy.novaborn.net/upload/images/activity/dfc4b68932ba4c9f907624bc424c48f4.png", "https://admin.cxy.novaborn.net/upload/images/activity/5ee1589cb6a1453cbf65d38c93c479bc.png")
        );

        wechatAutoSender.send(autoMessage);
    }
}
