package net.novaborn.takeaway.user.web.wx;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.web.wx.validator.WxValidator;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.api.BaseController;
import net.novaborn.takeaway.user.web.dto.AuthResponse;
import net.novaborn.takeaway.user.web.dto.WxAuthRequest;
import net.novaborn.takeaway.user.web.wx.vo.WxUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 微信登录认证专用类
 *
 * @author xiaohun
 * @Date 2017/8/24 14:22
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/wx")
public class WxController extends BaseController {

    private UserService userService;

    private JwtTokenUtil jwtTokenUtil;

    private WxValidator wxValidator;

    @PostMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(WxAuthRequest authRequest) {
        boolean validate = wxValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getCredenceName(), randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }

    @PostMapping(value = "setUserInfo")
    @ResponseBody
    public Tip setUserInfo(@ModelAttribute WxUserInfoVo wxUserInfoVo) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);

        // 如果用户不存在，抛出异常
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        BeanUtil.copyProperties(wxUserInfoVo, user.get(), CopyOptions.create().setIgnoreNullValue(true));
        user.get().setAvatar(wxUserInfoVo.getAvatarUrl());
        return userService.updateById(user.get()) ? new SuccessTip() : new ErrorTip(-1, "设置用户信息失败");

    }
}
