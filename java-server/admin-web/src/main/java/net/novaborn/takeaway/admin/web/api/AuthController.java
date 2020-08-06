package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.common.auth.validator.impl.DbValidator;
import net.novaborn.takeaway.admin.config.properties.JwtProperties;
import net.novaborn.takeaway.admin.web.dto.AuthRequest;
import net.novaborn.takeaway.admin.web.dto.AuthResponse;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.TimeUnit;

/**
 * 请求验证的
 *
 * @author xiaohun
 * @Date 2017/8/24 14:22
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class AuthController extends BaseController {
    private JwtProperties jwtProperties;

    private JwtTokenUtil jwtTokenUtil;

    private DbValidator dbValidator;

    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(@ModelAttribute AuthRequest authRequest) {
        boolean validate = dbValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);

            // 保存rediskey
            String redisKey = String.format("jwt:%s:%s", jwtTokenUtil.getUsernameFromToken(token), token);
            redisTemplate.opsForValue().set(redisKey, request.getRemoteHost(), jwtProperties.getExpiration(), TimeUnit.DAYS);

            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }


    @PostMapping(value = "${jwt.auth-path}/refresh")
    public String refresh() {
        return jwtTokenUtil.reGenerateToken(jwtTokenUtil.getToken(request));
    }
}
