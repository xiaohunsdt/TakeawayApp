package net.novaborn.takeaway.pay.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.pay.config.mq.OrderPayStatusQueueConfig;
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
@RabbitListener(queues = OrderPayStatusQueueConfig.QUEUE_NAME)
public class OrderPayStatusReceiver {

    private OrderService orderService;

    @SneakyThrows
    @RabbitHandler
    public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) {
        log.debug("订单过期队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Order target = orderService.getById(order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        if (target != null && target.getPayState() == PayState.UN_PAY && target.getOrderState() != OrderState.EXPIRED) {
            try {
                target.setOrderState(OrderState.EXPIRED);
                target.updateById();
            } catch (Exception e) {
                log.error("订单ID: {},设置订单为过期状态失败!重新方式队列中!!", order.getId());
                channel.basicReject(deliveryTag, true);
                return;
            }

        }

        channel.basicAck(deliveryTag, false);
    }
}