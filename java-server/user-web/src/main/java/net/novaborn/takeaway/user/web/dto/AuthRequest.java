package net.novaborn.takeaway.user.web.dto;

import net.novaborn.takeaway.user.common.auth.validator.dto.Credence;

/**
 * 认证的请求dto
 *
 * @author xiaohun
 * @Date 2017/8/24 14:00
 */
public class AuthRequest implements Credence {

    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getCredenceName() {
        return this.userName;
    }

    @Override
    public String getCredenceCode() {
        return this.password;
    }
}
