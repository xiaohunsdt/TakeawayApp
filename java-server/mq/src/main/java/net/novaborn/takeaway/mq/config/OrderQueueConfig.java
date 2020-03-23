package net.novaborn.takeaway.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 11/4/19
 * Time: 6:05 PM
 * Description:
 */
@Configuration
public class OrderQueueConfig {
    public final static String QUEUE_NAME = "delayed.live.queue.order-pay-expired";
    public final static String EXCHANGE_NAME = "delayed.live.exchange.order";

    @Bean
    public Queue orderPayExpiredQueue() {
        return new Queue(OrderQueueConfig.QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange orderExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(OrderQueueConfig.EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    // 绑定队列到交换器
    @Bean
    Binding orderBinding(Queue orderPayExpiredQueue, CustomExchange orderExchange) {
        return BindingBuilder.bind(orderPayExpiredQueue).to(orderExchange).with(OrderQueueConfig.QUEUE_NAME).noargs();
    }
}