package net.novaborn.takeaway.user.web.wx;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.common.auth.validator.impl.WxValidator;
import net.novaborn.takeaway.user.web.api.BaseController;
import net.novaborn.takeaway.user.web.dto.AuthRequest;
import net.novaborn.takeaway.user.web.dto.AuthResponse;
import net.novaborn.takeaway.user.web.dto.WxAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class WxAuthController extends BaseController {

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
}
