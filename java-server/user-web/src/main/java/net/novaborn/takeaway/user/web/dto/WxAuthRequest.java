package net.novaborn.takeaway.user.web.dto;

import lombok.Data;
import lombok.Setter;
import net.novaborn.takeaway.user.common.auth.validator.dto.Credence;

/**
 * 微信认证的请求dto
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:00
 */
@Setter
public class WxAuthRequest implements Credence {

    private String code;

    private String openId;

    @Override
    public String getCredenceName() {
        return this.openId;
    }

    @Override
    public String getCredenceCode() {
        return this.code;
    }
}
