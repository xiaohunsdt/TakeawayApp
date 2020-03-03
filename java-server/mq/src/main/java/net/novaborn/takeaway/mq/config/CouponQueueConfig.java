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
public class CouponQueueConfig {
    public final static String QUEUE_NAME = "delayed.live.queue.coupon-expired";
    public final static String EXCHANGE_NAME = "delayed.live.exchange.coupon";

    @Bean
    public Queue couponExpiredQueue() {
        return new Queue(CouponQueueConfig.QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange couponExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(CouponQueueConfig.EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    // 绑定队列到交换器
    @Bean
    Binding couponBinding(Queue couponExpiredQueue, CustomExchange couponExchange) {
        return BindingBuilder.bind(couponExpiredQueue).to(couponExchange).with(CouponQueueConfig.QUEUE_NAME).noargs();
    }
}
