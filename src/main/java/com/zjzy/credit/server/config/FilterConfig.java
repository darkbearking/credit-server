package com.zjzy.credit.server.config;

import javax.annotation.Resource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zjzy.credit.server.filter.ProviderAuthenticationFilter;
import com.zjzy.credit.server.filter.UserInfoFilter;

/**
 * @description Filter配置信息 
 * @author zhiwei.han
 * @date 2019-08-13 17:15:53
 */
@Configuration
public class FilterConfig {
    @Resource(name = "userInfoFilter")
    private UserInfoFilter userInfoFilter;
    @Resource(name = "providerAuthenticationFilter")
    private ProviderAuthenticationFilter providerAuthenticationFilter;

    /**
     * 注册远程服务调用的认证filter
     * @return FilterRegistrationBean RestAuthenticationFilter
     */
    @Bean
    public FilterRegistrationBean<ProviderAuthenticationFilter> restAuthenticationFilterRegister() {
        FilterRegistrationBean<ProviderAuthenticationFilter> registration = new FilterRegistrationBean<>();
        //注入过滤器
        registration.setFilter(providerAuthenticationFilter);
        //拦截规则
        registration.addUrlPatterns("/credit-server/*", "/credit-api/*");
        //过滤器名称
        registration.setName("providerAuthenticationFilter");
        //过滤器顺序
        registration.setOrder(1);
        //初始化变量
        registration.addInitParameter("whiteList",
                "/**/*.js;" + "/**/*.css;" + "/**/*.gif;" + "/credit-server/index.html");

        return registration;
    }

    /**
     * 注册远程服务调用的用户信息filter
     * @return FilterRegistrationBean UserInfoFilter
     */
    @Bean
    public FilterRegistrationBean<UserInfoFilter> restUserInfoFilteRegister() {
        FilterRegistrationBean<UserInfoFilter> registration = new FilterRegistrationBean<>();
        //注入过滤器
        registration.setFilter(userInfoFilter);
        //拦截规则
        registration.addUrlPatterns("/credit-server/*", "/credit-api/*");
        //过滤器名称
        registration.setName("userInfoFilter");
        //过滤器顺序
        registration.setOrder(2);
        //初始化变量
        registration.addInitParameter("whiteList",
                "/**/*.js;" + "/**/*.css;" + "/**/*.gif;" + "/credit-server/index.html");
        return registration;
    }

}
