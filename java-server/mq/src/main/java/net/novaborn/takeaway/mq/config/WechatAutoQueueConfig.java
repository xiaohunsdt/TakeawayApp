package net.novaborn.takeaway.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 11/4/19
 * Time: 6:05 PM
 * Description:
 */
@Configuration
public class WechatAutoQueueConfig {
    public final static String QUEUE_NAME = "direct.live.queue.wechat.auto";
    public final static String EXCHANGE_NAME = "direct.live.exchange.wechat";

    @Bean
    public Queue wechatAutoQueue() {
        return new Queue(WechatAutoQueueConfig.QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange wechatAutoExchange() {
        return new CustomExchange(WechatAutoQueueConfig.EXCHANGE_NAME, "direct", true, false);
    }

    // 绑定队列到交换器
    @Bean
    Binding wechatAutoBinding(Queue wechatAutoQueue, CustomExchange wechatAutoExchange) {
        return BindingBuilder.bind(wechatAutoQueue).to(wechatAutoExchange).with(WechatAutoQueueConfig.QUEUE_NAME).noargs();
    }
}
