package net.novaborn.takeaway.admin.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.entity.SysContext;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("自动接单队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        sysContext.setCurrentStoreId(order.getStoreId());

        try {
            orderController.receiveOrder(order.getId());
        } catch (Exception e) {
            log.error(null, e);
        }
        channel.basicAck(deliveryTag, false);
    }
}