package net.novaborn.takeaway.activity.signin.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 一周签到工具类
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class WeeklyService {
    private SignInService signInService;

    public boolean checkSignIn(String userId) {
//        SignIn signIn = getSignIn(userId);
//        if (signIn == null) {
//            return false;
//        }
        return false;
    }

    public SignIn getSignIn(String userId) {
        DateTime current = DateTime.now();
        return signInService.getSignIn(userId, current);
    }

    public void saveSignIn(String userId, SignIn signIn) {
        DateTime current = DateTime.now();
        signInService.saveSignIn(userId, current, signIn);
    }
}
