package net.novaborn.takeaway.pay.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.mq.config.OrderQueueConfig;
import net.novaborn.takeaway.order.entity.RefundLog;
import net.novaborn.takeaway.order.enums.RefundState;
import net.novaborn.takeaway.order.service.impl.RefundLogService;
import net.novaborn.takeaway.pay.services.impl.PayService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
@RabbitListener(queues = OrderQueueConfig.QUEUE_ORDER_WX_PAY_REFUND)
public class WxPayRefundReceiver {

    private RefundLogService refundLogService;

    private PayService payService;

    @RabbitHandler
    public void process(@Payload RefundLog refundLog, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.debug("微信退款队列接收时间: {}", DateUtil.formatDateTime(new Date()));

        RefundLog target = refundLogService.getById(refundLog.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        Tip tip = payService.refundPay(target);
        if (tip.getCode() != 0) {
            log.error((String) tip.getMessage());

            target.setRejectMsg((String) tip.getMessage());
            target.setState(RefundState.FAILED);
            refundLogService.updateById(target);
        }

        channel.basicAck(deliveryTag, false);
    }
}