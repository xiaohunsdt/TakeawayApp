package net.novaborn.takeaway.activity.web.api;

import net.novaborn.takeaway.activity.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.activity.common.auth.validator.IReqValidator;
import net.novaborn.takeaway.activity.common.exception.SysException;
import net.novaborn.takeaway.activity.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.activity.web.dto.AuthRequest;
import net.novaborn.takeaway.activity.web.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@Controller
@RequestMapping("/api/admin")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private IReqValidator reqValidator;

    @PostMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(@ModelAttribute AuthRequest authRequest) {
        boolean validate = reqValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
