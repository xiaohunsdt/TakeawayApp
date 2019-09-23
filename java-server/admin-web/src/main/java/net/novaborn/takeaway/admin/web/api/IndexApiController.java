package net.novaborn.takeaway.admin.web.api;

import com.alibaba.fastjson.JSON;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.common.ResponseModel;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.SuccessTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/api/admin/")
public class IndexApiController {
    @Autowired
    AdminService adminService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("index")
    @ResponseBody
    public SuccessTip index() {
        return new SuccessTip();
    }

    @GetMapping("getUserInfo")
    @ResponseBody
    public ResponseModel getUserInfo(HttpServletRequest request) {
        String userName = jwtTokenUtil.getUsernameFromToken(request);
        Optional<Admin> admin = adminService.getBaseMapper().selectByName(userName);
        admin.orElseThrow(()->new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        ResponseModel responseModel = new ResponseModel(admin);
        return responseModel;
    }
}
