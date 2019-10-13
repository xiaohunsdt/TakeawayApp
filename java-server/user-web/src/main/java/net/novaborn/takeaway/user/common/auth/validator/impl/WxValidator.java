package net.novaborn.takeaway.user.common.auth.validator.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import net.novaborn.takeaway.user.common.auth.validator.IReqValidator;
import net.novaborn.takeaway.user.common.auth.validator.dto.Credence;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号密码验证
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class WxValidator implements IReqValidator {

    private UserService userService;

    private WxMaService wxMaService;

    @Override
    public boolean validate(Credence credence) {
        String code = credence.getCredenceCode();
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());

            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
        return true;
    }
}
