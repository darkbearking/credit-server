package com.zjzy.credit.server.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zjzy.credit.common.consts.SecurityConsts;
import com.zjzy.credit.server.base.CurrentIdentityInfoHolder;
import com.zjzy.credit.server.utils.WebUtils;

import io.jsonwebtoken.Claims;

/**
 * @description 将用户的身份信息存储在threadlocal中，便于service层及其service层之后模块获取使用。
 * 用户可以是Person，也可以是外部程序. 
 * @author zhiwei.han
 * @date 2019-08-13 17:11:06
 */
@Component("userInfoFilter")
public class UserInfoFilter implements Filter {
    @Value("${credit.jwt.secret}")
    private String secret;

    // 白名单
    private static List<String> whiteList = new ArrayList<>();

    public UserInfoFilter() {
        super();
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList = WhiteListUtil.getWhiteList(filterConfig);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String uri = request.getRequestURI();

        uri = StringUtils.removeStart(uri, request.getContextPath());
        // 判断是否在白名单中
        if (WhiteListUtil.inWhiteList(uri, whiteList)) {
            chain.doFilter(req, res);
            return;
        }

        Claims claims = WebUtils.fetchClaims(request, secret);

        CurrentIdentityInfoHolder.setUserKey(claims.get(SecurityConsts.TOKEN_USER, String.class));

        chain.doFilter(req, res);

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

}
