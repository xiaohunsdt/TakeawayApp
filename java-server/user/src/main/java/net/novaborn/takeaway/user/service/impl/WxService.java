package net.novaborn.takeaway.user.service.impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.user.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class WxService implements IWxService {

    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setSessionKey(String openId, String sessionKey) {
        redisTemplate.boundValueOps("SessionKey:" + openId).set(sessionKey);
    }

    @Override
    public String getSessionKey(String openId) {
        return redisTemplate.boundValueOps("SessionKey:" + openId).get().toString();
    }
}
