package net.novaborn.takeaway.admin.web.api;

import net.novaborn.takeaway.common.entity.SysContext;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaohun
 */
@RequestMapping("/api/admin")
public class BaseController {
    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Resource
    SysContext sysContext;
}