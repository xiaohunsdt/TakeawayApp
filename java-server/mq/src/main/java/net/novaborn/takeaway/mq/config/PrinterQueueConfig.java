package net.novaborn.takeaway.mq.config;

import cn.hutool.core.util.RandomUtil;
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
public class PrinterQueueConfig {
    public final static String QUEUE_NAME = "direct.queue.printer";
    public final static String EXCHANGE_NAME = "direct.exchange.printer";

    @Bean
    public Queue printerQueue() {
        return new Queue(PrinterQueueConfig.QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange printerExchange() {
        return new CustomExchange(PrinterQueueConfig.EXCHANGE_NAME, "direct", true, false);
    }

    // 绑定队列到交换器
    @Bean
    Binding printerBinding(Queue printerQueue, CustomExchange printerExchange) {
        return BindingBuilder.bind(printerQueue).to(printerExchange).with(PrinterQueueConfig.QUEUE_NAME).noargs();
    }
}
