package net.novaborn.takeaway.mq.sender;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.mq.config.PrinterQueueConfig;
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
public class PrinterSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
        try {
            rabbitTemplate.convertAndSend(PrinterQueueConfig.EXCHANGE_NAME, PrinterQueueConfig.QUEUE_NAME, order);
        } catch (Exception e) {
            log.error("投递队列失败！！", e);
        }
    }
}
