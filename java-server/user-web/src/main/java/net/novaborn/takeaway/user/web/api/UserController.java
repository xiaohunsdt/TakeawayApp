package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.ResponseModel;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/user")
public class UserController extends BaseController {

    private UserService userService;

    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("getUserInfo")
    @ResponseBody
    public ResponseModel getUserInfo(HttpServletRequest request) {
        String userId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = Optional.ofNullable(userService.getBaseMapper().selectById(userId));
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        return new ResponseModel(user.get());
    }
}
