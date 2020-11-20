package net.novaborn.takeaway.user.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.utils.WxSubscrubeMessageUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
@RabbitListener(queues = OrderQueueConfig.QUEUE_ORDER_SUBSCRIBE_MESSAGE)
public class OrderSubscribeMessageReceiver {

    private OrderService orderService;

    private WxSubscrubeMessageUtil wxSubscrubeMessageUtil;

    @RabbitHandler
    public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("订单订阅消息队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Order target = orderService.getById(order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            if (target != null) {
                switch (order.getOrderState()) {
                    case PRODUCING:
                        wxSubscrubeMessageUtil.sendOrderReceiveMessage(target);
                        break;
                    case DELIVERING:
                        wxSubscrubeMessageUtil.sendOrderDeliveryMessage(target);
                        break;
                    case FINISHED:
                        wxSubscrubeMessageUtil.sendOrderFinishedMessage(target);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log.error(null, e);
        }

        channel.basicAck(deliveryTag, false);
    }
}