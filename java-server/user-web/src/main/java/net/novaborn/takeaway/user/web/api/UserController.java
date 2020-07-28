package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.impl.SignInService;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.wrapper.SignInWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/user")
public class UserController extends BaseController {

    private UserService userService;

    private JwtTokenUtil jwtTokenUtil;

    private SignInService signInService;

    @GetMapping("getSignInDays")
    @ResponseBody
    public Object getSignInDays() {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        SignIn signIn = signInService.getSignIn(user.get().getId(), new Date()).orElse(null);
        if (signIn == null) {
            signIn = new SignIn();
            signIn.setCreateDate(new Date());
        }
        return new SignInWrapper(signIn).warp();
    }

    @GetMapping("getSignInedCount")
    @ResponseBody
    public Integer getSignInedCount() {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        return signInService.getSignInedCount(user.get().getId(), new Date(), Calendar.WEEK_OF_MONTH);
    }
}
