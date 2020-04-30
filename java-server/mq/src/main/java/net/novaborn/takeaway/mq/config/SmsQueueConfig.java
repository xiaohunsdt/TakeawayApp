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
public class SmsQueueConfig {
    public final static String QUEUE_NAME = "delayed.live.queue.sms";
    public final static String EXCHANGE_NAME = "delayed.live.exchange.sms";

    @Bean
    public Queue smsQueue() {
        return new Queue(SmsQueueConfig.QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange smsExchange() {
        return new CustomExchange(SmsQueueConfig.EXCHANGE_NAME, "direct", true, false);
    }

    // 绑定队列到交换器
    @Bean
    Binding smsBinding(Queue smsQueue, CustomExchange smsExchange) {
        return BindingBuilder.bind(smsQueue).to(smsExchange).with(SmsQueueConfig.QUEUE_NAME).noargs();
    }
}
