package net.novaborn.takeaway.task;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.admin.task.WechatAutoTask;
import net.novaborn.takeaway.mq.dto.AutoMessage;
import net.novaborn.takeaway.mq.sender.WechatAutoSender;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class WechatAutoTaskTest {
    @Autowired
    OrderService orderService;

    @Autowired
    WechatAutoTask wechatAutoTask;

    @Autowired
    WechatAutoSender wechatAutoSender;

    @Test
    public void sendTest() {
        ArrayList<String> imgs = new ArrayList<>();
        imgs.add("https://admin.cxy.novaborn.net/upload/images/6b982206490c463080cb0a3acdcd1914.jpg");
        imgs.add("https://admin.cxy.novaborn.net/upload/images/50f33b4172e84d45b477589770aec59d.jpeg");

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage("正宗周黑鸭豪横大礼包来啦~~包含鸭头/鸭翅/鸭锁骨/鸭脖/猪蹄，原价31000，礼包价只要26000!!鸭货口味调整完成!挑战全韩国最好吃的鸭货,你不来尝尝？!数量不多,先到先得!!");
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);
    }

    @Test
    public void goodsShowTest() {
        wechatAutoTask.goodsShow();
    }

    @Test
    public void orderShowTest() {
        Order order = orderService.getById("105779e2cfa5a8f8795746fac687872e");
        wechatAutoTask.orderShow(order);
    }

    @Test
    public void appointmentShowTest() {
        wechatAutoTask.appointmentShow();
    }
}
