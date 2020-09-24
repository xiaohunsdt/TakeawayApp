package net.novaborn.takeaway.im.web;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.im.web.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseEntity<String> createAuthenticationToken() {
        return ResponseEntity.ok("String");
    }

    @ResponseBody
    @RequestMapping(value = "${jwt.auth-path}/refresh")
    public AuthResponse refresh() {
        return new AuthResponse("a","a");
    }
}
