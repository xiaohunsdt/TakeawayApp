package net.novaborn.takeaway.pay.mq;

import cn.hutool.core.date.DateUnit;
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
import net.novaborn.takeaway.pay.services.impl.PayService;
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

    private PayService payService;

    private OrderPayStatusSender orderPayStatusSender;

    @SneakyThrows
    @RabbitHandler
    public void process(@Payload String orderId, Channel channel, @Headers Map<String, Object> headers) {
        log.debug("订单支付状态队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Order target = orderService.getById(orderId);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            if (target != null && target.getPayState() == PayState.UN_PAY && target.getOrderState() != OrderState.EXPIRED) {
                payService.confirmPay(target.getId());
            }
        } catch (Exception e) {
            // 如果15分钟之内还没有确认支付,视为支付失败!!设置订单状态为过去过期!
            if (DateUtil.between(target.getCreateDate(), DateUtil.date(), DateUnit.MINUTE) > 15) {
                target.setOrderState(OrderState.EXPIRED);
                target.updateById();
                log.warn("订单:{} 支付验证过期!设置订单为过期订单", orderId);
            } else {
                // 验证失败再次将这个订单丢到延迟队列当中
                orderPayStatusSender.send(orderId, 30);
                log.error("订单:{} 验证支付失败!原因: {}, 重新丢回延迟队列中!", orderId, e.getMessage());
            }
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }
}