package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.common.ResponseModel;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
public class IndexController extends BaseController{
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
        admin.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        ResponseModel responseModel = new ResponseModel(admin);
        return responseModel;
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Tip uploadImg(@RequestParam MultipartFile file) {
        String imgName;

        if (!file.isEmpty()) {
            try {
                String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                imgName = IdUtil.fastSimpleUUID() + prefix;
                // 文件保存路径
//                String filePath = new File(request.getSession().getServletContext().getRealPath("/")).getParent() + "/upload/leaguePics/";
                String filePath = new File(System.getProperty("user.dir")) + "/upload/images/";

                // 转存文件
                File dir = new File(filePath);
                if (!dir.exists() && !dir.isDirectory()) {
                    dir.mkdirs();
                }

                file.transferTo(new File(filePath + imgName));
            } catch (Exception e) {
                log.error("", e);
                throw new SysException(SysExceptionEnum.UPLOAD_IMAGE_FAILED);
            }
        } else {
            throw new SysException(SysExceptionEnum.UPLOAD_IMAGE_FAILED);
        }
        return new SuccessTip(imgName);
    }
}
