package net.novaborn.takeaway.admin.mq;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.entity.SysContext;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.mq.config.CouponQueueConfig;
import net.novaborn.takeaway.mq.sender.CouponExpiredSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
@RabbitListener(queues = CouponQueueConfig.QUEUE_NAME)
public class CouponExpiredReceiver {
    private CouponExpiredSender couponExpiredSender;

    private CouponService couponService;

    private SysContext sysContext;

    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void process(@Payload Coupon coupon, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.debug("优惠卷过期队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        sysContext.setCurrentStoreId(coupon.getStoreId());
        Coupon target = couponService.getById(coupon.getId());
        if (target != null) {
            Date current = new Date();
            if (!target.getExpireDate().equals(coupon.getExpireDate()) && target.getExpireDate().after(current)) {
                //今天内过期
                if (DateUtil.isSameDay(current, target.getExpireDate())) {
                    long offset = DateUtil.between(current, target.getExpireDate(), DateUnit.SECOND);
                    if (offset > 0) {
                        couponExpiredSender.send(target, (int) offset);
                    }
                }
            } else {
                if (target.getState() != CouponState.EXPIRED) {
                    target.setState(CouponState.EXPIRED);
                    couponService.updateById(target);
                }
            }
        }

        channel.basicAck(deliveryTag, false);
    }
}