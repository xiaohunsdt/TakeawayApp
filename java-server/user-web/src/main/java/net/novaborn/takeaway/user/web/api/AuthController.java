package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.common.auth.validator.impl.DbValidator;
import net.novaborn.takeaway.user.web.dto.AuthRequest;
import net.novaborn.takeaway.user.web.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class AuthController extends BaseController {

    private JwtTokenUtil jwtTokenUtil;

    private DbValidator dbValidator;

    @PostMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(@ModelAttribute AuthRequest authRequest) {
        boolean validate = dbValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
