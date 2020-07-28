package net.novaborn.takeaway.user.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.impl.SignInService;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.coupon.services.impl.CouponTemplateService;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Calendar;
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
@RabbitListener(queues = OrderQueueConfig.QUEUE_ORDER_SIGN_IN)
public class OrderSignInReceiver {

    private SignInService signInService;

    private CouponService couponService;
    private CouponTemplateService couponTemplateService;

    @SneakyThrows
    @RabbitHandler
    public void process(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) {
        log.info("订单签到消息队列接收时间: {}", DateUtil.formatDateTime(new Date()));
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        try {
            Date current = new Date();
            signInService.signIn(order.getUserId(), order.getCreateDate());
            int signInedCount = signInService.getSignInedCount(order.getUserId(), current, Calendar.WEEK_OF_MONTH);
            if (signInedCount >= 7) {
                int weekOfMonth = DateUtil.weekOfMonth(current);
                SignIn signIn = signInService.getSignIn(order.getUserId(), current).get();
                CouponTemplate couponTemplate = couponTemplateService.getById("968f9d2cf1eedd6f798f05bfbdb5d0a7");
                if (couponTemplate != null && !signIn.getWeekExchangedList().contains(weekOfMonth)) {
                    couponService.generateCoupon(couponTemplate, signIn.getUserId());
                    signIn.getWeekExchangedList().add(weekOfMonth);
                    signInService.saveSignIn(signIn.getUserId(), signIn.getCreateDate(), signIn);
                }
            }
        } catch (Exception e) {
            log.error("订单ID: {},设置订单签到失败!重新方式队列中!!", order.getId());
            channel.basicReject(deliveryTag, true);
            return;
        }

        channel.basicAck(deliveryTag, false);
    }
}