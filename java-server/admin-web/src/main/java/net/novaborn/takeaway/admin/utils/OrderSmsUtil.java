package net.novaborn.takeaway.admin.utils;

import cn.hutool.core.thread.ThreadUtil;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.mq.dto.SmsDto;
import net.novaborn.takeaway.mq.sender.OrderSubscribeMessageSender;
import net.novaborn.takeaway.mq.sender.SmsSender;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
@Setter(onMethod_ = {@Autowired})
@Component
public class OrderSmsUtil {
    private OrderService orderService;

    private AddressService addressService;

    private SmsSender smsSender;

    private OrderSubscribeMessageSender orderSubscribeMessageSender;

    @Setter
    private DelayQueue<Message> queue;

    private static Map<OrderState, String> orderStateStringMap = new HashMap<>();

    private final int DELAY_TIME = 3;

    static {
        orderStateStringMap.put(OrderState.PRODUCING, "[川香苑系统消息]\n\n尊敬的顾客，您好！\n\n订单ID: %s\n\n已经受理！正在为您制作，请耐心等待！\n详情请到订单详细页面查看");
        orderStateStringMap.put(OrderState.DELIVERING, "[川香苑系统消息]\n\n尊敬的顾客，您好！\n\n订单ID: %s\n\n已开始配送！请稍等片刻！\n详情请到订单详细页面查看");
        orderStateStringMap.put(OrderState.FINISHED, "[川香苑系统消息]\n\n尊敬的顾客，您好！\n\n订单ID: %s\n\n已完成！就餐过后请对本单进行评价！我们的进步离不开您的宝贵意见，感谢您的支持！");
    }

    public OrderSmsUtil() {
        queue = new DelayQueue<>();
        ThreadUtil.execute(new Consumer());
    }

    public boolean pushMessage(Order order) {
        orderSubscribeMessageSender.send(order);

        Message item = new Message(order, DELAY_TIME * 1000);
        return !queue.contains(item) && queue.add(item);
    }

    @Data
    class Message implements Delayed {
        // 消息内容
        private Order order;

        // 延迟时长，这个是必须的属性因为要按照这个判断延时时长
        private long executeTime;

        public Message(Order order, long delayTime) {
            this.order = order;
            this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        }

        // 自定义实现比较方法返回 1 0 -1三个参数
        @Override
        public int compareTo(Delayed delayed) {
            Message msg = (Message) delayed;
            return this.order.getCreateDate().compareTo(msg.getOrder().getCreateDate());
        }

        // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public boolean equals(Object o) {
            return this.getOrder().equals(((Message) o).getOrder());
        }

        @Override
        public int hashCode() {
            return Objects.hash(order);
        }
    }

    class Consumer implements Runnable {
        // 延时队列 ,消费者从其中获取消息进行消费
        @Override
        public void run() {
            while (true) {
                try {
                    Message take = queue.take();
                    log.info("[SMS] 订单消息id：{} 消息体：{}", take.order.getId(), take.getOrder());
//                    redisTemplate.opsForValue().setIfAbsent("sms:order:" + take.order.getId(), "", DELAY_TIME, TimeUnit.SECONDS);

                    Order _order = orderService.getById(take.order.getId());
                    Address address = addressService.getById(_order.getAddressId());
                    String msg = String.format(orderStateStringMap.get(_order.getOrderState()), _order.getId());

                    if (!_order.getOrderState().equals(OrderState.DELIVERING)) {
                        smsSender.send(new SmsDto(address.getPhone(), msg));
                    }
//                    orderSubscribeMessageSender.send(_order);
                } catch (InterruptedException e) {
                    log.error(null, e);
                }
            }
        }
    }
}
