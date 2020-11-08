package net.novaborn.takeaway.user.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

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
@RabbitListener(queues = OrderQueueConfig.QUEUE_ORDER_PAY_EXPIRED)
public class OrderPayExpiredReceiver {

    private OrderService orderService;

    private OrderItemService orderItemService;

    private GoodsService goodsService;

    private GoodsStockService goodsStockService;

    @SneakyThrows
    @RabbitHandler
    public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) {
        log.info("订单过期队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Order target = orderService.getById(order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        if (target != null && target.getPayState() == PayState.UN_PAY && target.getOrderState() != OrderState.EXPIRED) {
            try {
                target.setOrderState(OrderState.EXPIRED);
                orderService.updateById(target);

                // 恢复库存
                orderItemService.selectByOrderId(order.getId()).parallelStream().forEach(item -> {
                    Optional<Goods> goods = Optional.ofNullable(goodsService.getById(item.getGoodsId()));
                    if (goods.isEmpty()) {
                        return;
                    }
                    goodsStockService.recoverStock(goods.get(), item.getGoodsCount());
                });
            } catch (Exception e) {
                log.error("订单ID: {},设置订单为过期状态失败!重新方式队列中!!", target.getId());
                channel.basicReject(deliveryTag, true);
                return;
            }
        }

        channel.basicAck(deliveryTag, false);
    }
}