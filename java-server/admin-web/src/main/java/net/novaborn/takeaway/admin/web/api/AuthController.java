package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.common.auth.validator.impl.DbValidator;
import net.novaborn.takeaway.admin.config.properties.JwtProperties;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
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
    private AdminService adminService;

    private JwtProperties jwtProperties;

    private JwtTokenUtil jwtTokenUtil;

    private DbValidator dbValidator;

    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(@ModelAttribute AuthRequest authRequest) {
        boolean validate = dbValidator.validate(authRequest);

        if (validate) {
            Admin admin = adminService.getBaseMapper().selectByName(authRequest.getUserName()).get();

            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(admin.getId().toString(), randomKey);

            // 保存到redis
            String redisKey = jwtTokenUtil.getRedisKey(token);
            redisTemplate.opsForValue().set(redisKey, request.getRemoteHost(), jwtProperties.getExpiration(), TimeUnit.SECONDS);

            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "${jwt.auth-path}/refresh")
    public AuthResponse refresh() {
        String newToken = jwtTokenUtil.reGenerateToken(jwtTokenUtil.getToken(request));

        //删除旧的token
        redisTemplate.delete(jwtTokenUtil.getRedisKey(jwtTokenUtil.getToken(request)));
        redisTemplate.opsForValue().set(jwtTokenUtil.getRedisKey(newToken), request.getRemoteHost(), jwtProperties.getExpiration(), TimeUnit.SECONDS);
        return new AuthResponse(newToken, null);
    }
}
