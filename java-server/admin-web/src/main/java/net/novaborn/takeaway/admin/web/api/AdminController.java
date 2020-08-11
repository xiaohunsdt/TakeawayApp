package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class AdminController extends BaseController {
    private AdminService adminService;

    private JwtTokenUtil jwtTokenUtil;

    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("getAdminInfo")
    @ResponseBody
    public Admin getAdminInfo() {
        String adminName = jwtTokenUtil.getUsernameFromToken(request);
        Optional<Admin> admin = adminService.getBaseMapper().selectByName(adminName);
        admin.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        return admin.get();
    }

    @PostMapping("logout")
    public ResponseEntity logout() {
        redisTemplate.delete(jwtTokenUtil.getRedisKey(jwtTokenUtil.getToken(request)));
        return ResponseEntity.ok(0);
    }
}
