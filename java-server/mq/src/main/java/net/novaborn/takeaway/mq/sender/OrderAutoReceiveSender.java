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
public class OrderAutoReceiveSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Long orderId) {
        try {
            rabbitTemplate.convertAndSend(OrderQueueConfig.DIRECT_EXCHANGE, OrderQueueConfig.QUEUE_ORDER_AUTO_RECEIVE, orderId);
        } catch (Exception e) {
            log.error("投递队列失败！！", e);
        }
    }
}
