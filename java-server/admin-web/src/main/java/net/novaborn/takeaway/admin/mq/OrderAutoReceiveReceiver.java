package net.novaborn.takeaway.admin.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.SysContext;
import net.novaborn.takeaway.admin.web.api.OrderController;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 11/4/19
 * Time: 6:15 PM
 * Description:
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Component
@RabbitListener(queues = OrderQueueConfig.QUEUE_ORDER_AUTO_RECEIVE)
//@RabbitListener(
//        bindings = @QueueBinding(
//                value = @Queue(value = PrinterQueueConfig.QUEUE_NAME),
//                exchange = @Exchange(value = PrinterQueueConfig.EXCHANGE_NAME),
//                key = PrinterQueueConfig.QUEUE_NAME)
//)
public class OrderAutoReceiveReceiver {
    private OrderController orderController;

    private SysContext sysContext;

    @SneakyThrows
    @RabbitHandler
    public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) {
        log.debug("自动接单队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        sysContext.setCurrentStoreId(order.getStoreId());
        orderController.receiveOrder(order.getId());
        channel.basicAck(deliveryTag, false);
    }
}