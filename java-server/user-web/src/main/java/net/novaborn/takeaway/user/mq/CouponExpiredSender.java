package net.novaborn.takeaway.user.mq;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.user.config.mq.CouponQueueConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 11/4/19
 * Time: 6:17 PM
 * Description:
 */
@Slf4j
@Component
public class CouponExpiredSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Coupon coupon, int delaySeconds) {
//        CorrelationData cd = new CorrelationData();
//        cd.setId(order.getId());

        rabbitTemplate.convertAndSend(CouponQueueConfig.EXCHANGE_NAME, CouponQueueConfig.QUEUE_NAME, coupon, message -> {
            message.getMessageProperties().setHeader("x-delay", delaySeconds * 1000);
            return message;
        });
    }
}
