package net.novaborn.takeaway.admin.config;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.filter.AuthFilter;
import net.novaborn.takeaway.admin.common.auth.security.DataSecurityAction;
import net.novaborn.takeaway.admin.common.auth.security.impl.Base64SecurityAction;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.common.xss.XssFilter;
import net.novaborn.takeaway.admin.config.properties.JwtProperties;
import net.novaborn.takeaway.admin.config.properties.RestProperties;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * web配置
 *
 * @author xiaohun
 * @date 2017-08-23 15:48
 */
@Slf4j
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/view/index.html");
        registry.addResourceHandler("/res/**").addResourceLocations("classpath:/res/");

        log.info("upload文件夹为：{}", System.getProperty("user.dir") + File.separator + "upload" + File.separator);
        if (!"/".equals(System.getProperty("user.dir"))) {
            registry.addResourceHandler("/upload/**").addResourceLocations("file:" + System.getProperty("user.dir") + File.separator + "upload" + File.separator);
        } else {
            registry.addResourceHandler("/upload/**").addResourceLocations("file:" + File.separator + "upload" + File.separator);
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "auth-open", havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean jwtAuthenticationTokenFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setJwtProperties(this.jwtProperties);
        authFilter.setJwtTokenUtil(this.jwtTokenUtil);
        authFilter.setRedisTemplate(redisTemplate);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(authFilter);

        //添加不需要忽略的格式信息.
        List<String> exclusions = new ArrayList<>();
        exclusions.add("/api/admin/" + jwtProperties.getAuthPath());
//        exclusions.add("/api/client/" + jwtProperties.getAuthPath());
        filterRegistrationBean.addInitParameter("exclusions", CollectionUtil.join(exclusions, ","));
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

    @Bean
    public DataSecurityAction dataSecurityAction() {
        return new Base64SecurityAction();
    }

    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        XssFilter xssFilter = new XssFilter();
//        xssFilter.setUrlExclusion(Arrays.asList("/notice/update", "/notice/add"));
        FilterRegistrationBean registration = new FilterRegistrationBean(xssFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
