package net.novaborn.takeaway.user.mq;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.user.config.mq.OrderPayExpiredQueueConfig;
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
public class OrderPayExpiredSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order, int delaySeconds) {
        CorrelationData cd = new CorrelationData();
        cd.setId(order.getId());

        rabbitTemplate.convertAndSend(OrderPayExpiredQueueConfig.EXCHANGE_NAME, OrderPayExpiredQueueConfig.QUEUE_NAME, order, message -> {
            message.getMessageProperties().setHeader("x-delay", delaySeconds * 1000);
            return message;
        }, cd);
    }
}
