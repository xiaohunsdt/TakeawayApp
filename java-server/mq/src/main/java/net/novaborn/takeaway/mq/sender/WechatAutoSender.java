package net.novaborn.takeaway.mq.sender;

import com.alibaba.fastjson.JSON;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.mq.config.WechatAutoQueueConfig;
import net.novaborn.takeaway.mq.dto.AutoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 11/4/19
 * Time: 6:17 PM
 * Description: 微信自动发朋友圈队列
 */
@Slf4j
@Component
@Setter(onMethod_ = {@Autowired})
public class WechatAutoSender {

    private RabbitTemplate rabbitTemplate;

    public void send(AutoMessage autoMessage) {
        try {
            rabbitTemplate.convertAndSend(WechatAutoQueueConfig.EXCHANGE_NAME, WechatAutoQueueConfig.QUEUE_NAME, JSON.toJSONBytes(autoMessage));
        } catch (Exception e) {
            log.error("投递队列失败！！", e);
        }
    }
}
