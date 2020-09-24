package net.novaborn.takeaway.im.web;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaohun
 */
@RequestMapping("/api/im")
public class BaseController {
    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;
}