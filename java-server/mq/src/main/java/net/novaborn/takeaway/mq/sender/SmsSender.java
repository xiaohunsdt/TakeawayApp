package net.novaborn.takeaway.mq.sender;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.mq.config.CouponQueueConfig;
import net.novaborn.takeaway.mq.config.SmsQueueConfig;
import net.novaborn.takeaway.mq.dto.SmsDto;
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
public class SmsSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(SmsDto sms) {
        rabbitTemplate.convertAndSend(SmsQueueConfig.EXCHANGE_NAME, SmsQueueConfig.QUEUE_NAME, sms);
    }
}
