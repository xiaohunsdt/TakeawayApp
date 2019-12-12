package net.novaborn.takeaway.pay.config.mq;

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
public class OrderPayStatusQueueConfig {
    public final static String QUEUE_NAME = "delayed.live.queue.order-pay-status-check";
    public final static String EXCHANGE_NAME = "delayed.live.exchange";

    @Bean
    public Queue queue() {
        return new Queue(OrderPayStatusQueueConfig.QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(OrderPayStatusQueueConfig.EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    // 绑定队列到交换器
    @Bean
    Binding binding(Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(OrderPayStatusQueueConfig.QUEUE_NAME).noargs();
    }
}
