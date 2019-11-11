package net.novaborn.takeaway.pay.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import net.novaborn.takeaway.pay.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.pay.web.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信登录认证专用类
 *
 * @author xiaohun
 * @Date 2017/8/24 14:22
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/wx")
public class WxController extends BaseController {

    private JwtTokenUtil jwtTokenUtil;

    private WxMaService wxMaService;

    @PostMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestParam String code) {

        WxMaJscode2SessionResult session = null;
        try {
            session = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(session.getOpenid(), randomKey);

        log.info("OpenId: {} 登陆成功!",session.getOpenid());
        return ResponseEntity.ok(new AuthResponse(token, randomKey));
    }
}
