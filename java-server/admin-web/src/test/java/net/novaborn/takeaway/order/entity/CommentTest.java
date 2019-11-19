package net.novaborn.takeaway.order.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.impl.CommentService;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class CommentTest {
    @Autowired
    CommentService commentService;

    @Test
    public void createTest() {
        Comment comment = new Comment();
        comment.setOrderId("1111");
        comment.setUserId("zxczc");
        comment.setDelicious(3);
        comment.setExpress(4);
        comment.setService(5);
        comment.setComment("comment1111");
        comment.insert();
    }

    @Test
    public void getTest() {
        System.out.println(commentService.getById("5d29656df8bcdeb28e8dd65f5e7d1b9e"));
    }

    @Test
    public void updateTest() {

    }
}
