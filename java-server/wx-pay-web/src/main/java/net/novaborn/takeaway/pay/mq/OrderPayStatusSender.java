package net.novaborn.takeaway.pay.mq;

import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    public void send(Order order, Date startDate, int delaySeconds) {
        // 设置开始支付的时间
        if (startDate != null) {
            order.setUpdateDate(startDate);
        }
        rabbitTemplate.convertAndSend(OrderQueueConfig.DELAYED_EXCHANGE, OrderQueueConfig.QUEUE_ORDER_WX_PAY_CHECK, order, message -> {
            message.getMessageProperties().setHeader("x-delay", delaySeconds * 1000);
            return message;
        });
    }
}
