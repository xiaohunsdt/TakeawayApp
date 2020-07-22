package net.novaborn.takeaway.activity.signin.service.impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class SignInService implements ISignInService {
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Optional<SignIn> getSignIn(String userId, Date date) {
        String key = getKeyStr(userId, date);
        return Optional.ofNullable((SignIn) redisTemplate.opsForValue().get(key));
    }

    @Override
    public void saveSignIn(String userId, Date date, SignIn signIn) {
        String key = getKeyStr(userId, date);
        redisTemplate.opsForValue().set(key, signIn, 7, TimeUnit.DAYS);
    }

    @Override
    public boolean checkSignIn(String userId, Date date, Calendar dateUnit) {
        Optional<SignIn> signIn = getSignIn(userId, date);
        if (signIn.isEmpty()) {
            return false;
        }

        return checkSignIn(signIn.get(), date, dateUnit);
    }

    @Override
    public boolean checkSignIn(SignIn signIn, Date date, Calendar dateUnit) {
        return false;
    }
}
