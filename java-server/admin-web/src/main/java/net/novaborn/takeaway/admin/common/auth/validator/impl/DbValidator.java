package net.novaborn.takeaway.admin.common.auth.validator.impl;

import net.novaborn.takeaway.admin.common.auth.validator.IReqValidator;
import net.novaborn.takeaway.admin.common.auth.validator.dto.Credence;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号密码验证
 *
 * @author xiaohun
 * @date 2017-08-23 12:34
 */
@Service
public class DbValidator implements IReqValidator {

    @Autowired
    AdminService adminService;

    @Override
    public boolean validate(Credence credence) {
        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        return adminService.login(userName, password);
    }
}
