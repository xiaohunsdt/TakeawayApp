package net.novaborn.takeaway.user.web.filter;

import cn.hutool.http.HttpUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageServerFilter implements Filter {
    private String serverUrl;

    public ImageServerFilter(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        byte[] datas;
        try {
            datas = HttpUtil.createGet(this.serverUrl + uri).execute().bodyBytes();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        response.getOutputStream().write(datas);
    }

    @Override
    public void destroy() {
    }
}
