package net.novaborn.takeaway.pay.common.auth.validator.impl;

import net.novaborn.takeaway.pay.common.auth.validator.IReqValidator;
import net.novaborn.takeaway.pay.common.auth.validator.dto.Credence;
import org.springframework.stereotype.Service;

/**
 * 直接验证账号密码是不是admin
 *
 * @author xiaohun
 * @date 2017-08-23 12:34
 */
@Service
public class SimpleValidator implements IReqValidator {

    private static String USER_NAME = "admin";

    private static String PASSWORD = "betting123!";

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        if (USER_NAME.equals(userName) && PASSWORD.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
