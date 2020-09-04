package net.novaborn.takeaway.pay.mq;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.pay.config.mq.OrderPayStatusQueueConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
public class OrderPayStatusSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Long orderId, int delaySeconds) {
        CorrelationData cd = new CorrelationData();
        cd.setId(orderId.toString());

        rabbitTemplate.convertAndSend(OrderPayStatusQueueConfig.EXCHANGE_NAME, OrderPayStatusQueueConfig.QUEUE_NAME, orderId, message -> {
            message.getMessageProperties().setHeader("x-delay", delaySeconds * 1000);
            return message;
        }, cd);
    }
}
