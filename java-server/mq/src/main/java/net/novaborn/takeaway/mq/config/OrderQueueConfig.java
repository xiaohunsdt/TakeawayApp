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
    public final static String QUEUE_ORDER_PAY_EXPIRED = "delayed.queue.order.pay.expired";
    public final static String QUEUE_ORDER_SUBSCRIBE_MESSAGE = "direct.queue.order.subscribe.message";
    public final static String QUEUE_ORDER_SIGN_IN = "direct.queue.order.signin";
    public final static String QUEUE_ORDER_AUTO_RECEIVE = "direct.queue.order.auto.receive";
    public final static String QUEUE_ORDER_WX_PAY_CHECK = "delayed.queue.order.wx_pay.check";
    public final static String QUEUE_ORDER_WX_PAY_REFUND = "delayed.queue.order.wx_pay.refund";
    public final static String DELAYED_EXCHANGE = "delayed.exchange.order";
    public final static String DIRECT_EXCHANGE = "direct.exchange.order";

    @Bean
    public Queue orderPayExpiredQueue() {
        return new Queue(OrderQueueConfig.QUEUE_ORDER_PAY_EXPIRED);
    }

    @Bean
    public Queue orderSubscribeMessageQueue() {
        return new Queue(OrderQueueConfig.QUEUE_ORDER_SUBSCRIBE_MESSAGE);
    }

    @Bean
    public Queue orderSignInQueue() {
        return new Queue(OrderQueueConfig.QUEUE_ORDER_SIGN_IN);
    }

    @Bean
    public Queue orderAutoReceiveQueue() {
        return new Queue(OrderQueueConfig.QUEUE_ORDER_AUTO_RECEIVE);
    }

    @Bean
    public Queue orderWxPayCheckQueue() {
        return new Queue(OrderQueueConfig.QUEUE_ORDER_WX_PAY_CHECK);
    }

    @Bean
    public Queue orderWxPayRefundQueue() {
        return new Queue(OrderQueueConfig.QUEUE_ORDER_WX_PAY_REFUND);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange orderDelayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(OrderQueueConfig.DELAYED_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    CustomExchange orderDirectExchange() {
        return new CustomExchange(OrderQueueConfig.DIRECT_EXCHANGE, "direct", true, false);
    }

    // 绑定队列到交换器
    @Bean
    Binding orderBinding(Queue orderPayExpiredQueue, CustomExchange orderDelayedExchange) {
        return BindingBuilder.bind(orderPayExpiredQueue).to(orderDelayedExchange).with(OrderQueueConfig.QUEUE_ORDER_PAY_EXPIRED).noargs();
    }

    @Bean
    Binding orderBinding2(Queue orderSubscribeMessageQueue, CustomExchange orderDirectExchange) {
        return BindingBuilder.bind(orderSubscribeMessageQueue).to(orderDirectExchange).with(OrderQueueConfig.QUEUE_ORDER_SUBSCRIBE_MESSAGE).noargs();
    }

    @Bean
    Binding orderBinding3(Queue orderSignInQueue, CustomExchange orderDirectExchange) {
        return BindingBuilder.bind(orderSignInQueue).to(orderDirectExchange).with(OrderQueueConfig.QUEUE_ORDER_SIGN_IN).noargs();
    }

    @Bean
    Binding orderBinding4(Queue orderAutoReceiveQueue, CustomExchange orderDirectExchange) {
        return BindingBuilder.bind(orderAutoReceiveQueue).to(orderDirectExchange).with(OrderQueueConfig.QUEUE_ORDER_AUTO_RECEIVE).noargs();
    }

    @Bean
    Binding orderBinding5(Queue orderWxPayCheckQueue, CustomExchange orderDelayedExchange) {
        return BindingBuilder.bind(orderWxPayCheckQueue).to(orderDelayedExchange).with(OrderQueueConfig.QUEUE_ORDER_WX_PAY_CHECK).noargs();
    }

    @Bean
    Binding orderBinding6(Queue orderWxPayRefundQueue, CustomExchange orderDirectExchange) {
        return BindingBuilder.bind(orderWxPayRefundQueue).to(orderDirectExchange).with(OrderQueueConfig.QUEUE_ORDER_WX_PAY_REFUND).noargs();
    }
}
