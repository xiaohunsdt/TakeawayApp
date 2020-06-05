package net.novaborn.takeaway.mq;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.mq.sender.SmsSender;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class SmsSenderTest {
    @Autowired
    private SmsSender smsSender;
}
