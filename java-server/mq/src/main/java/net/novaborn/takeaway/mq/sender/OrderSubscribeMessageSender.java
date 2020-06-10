package net.novaborn.takeaway.mq.sender;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.Order;
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
public class OrderSubscribeMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
        rabbitTemplate.convertAndSend(OrderQueueConfig.DIRECT_EXCHANGE, OrderQueueConfig.QUEUE_ORDER_SUBSCRIBE_MESSAGE, order);
    }
}
