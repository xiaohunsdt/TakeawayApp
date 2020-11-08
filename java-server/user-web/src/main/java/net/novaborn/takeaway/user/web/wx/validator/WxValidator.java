package net.novaborn.takeaway.user.web.wx.validator;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import net.novaborn.takeaway.user.common.auth.validator.IReqValidator;
import net.novaborn.takeaway.user.common.auth.validator.dto.Credence;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.service.impl.WxService;
import net.novaborn.takeaway.user.web.dto.WxAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * 账号密码验证
 *
 * @author xiaohun
 * @date 2017-08-23 12:34
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class WxValidator implements IReqValidator {

    private UserService userService;

    private WxService wxService;

    private WxMaService wxMaService;

    @Override
    public boolean validate(Credence credence) {
        String code = credence.getCredenceCode();
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.debug(session.getSessionKey());
            log.debug(session.getOpenid());

            Optional<User> user = userService.selectByOpenId(session.getOpenid()).or(() -> {
                // 创建一个新的用户并保存在数据库中
                User newUser = new User();
                newUser.setOpenId(session.getOpenid());
                newUser.insert();
                return Optional.of(newUser);
            });

            //设置最后登陆时间
            user.get().setLastLoginDate(new Date());
            userService.updateById(user.get());

            //将 openId 和 sessionkey 保存在redis中
            ((WxAuthRequest) credence).setOpenId(session.getOpenid());
            wxService.setSessionKey(session.getOpenid(), session.getSessionKey());
        } catch (WxErrorException e) {
            log.error(null, e);
        }
        return true;
    }
}
