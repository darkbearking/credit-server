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
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zjzy.credit.common.consts.Consts;
import com.zjzy.credit.common.consts.SecurityConsts;
import com.zjzy.credit.common.model.ResultInfo;
import com.zjzy.credit.common.utils.JsonUtils;
import com.zjzy.credit.server.utils.WebUtils;

import io.jsonwebtoken.Claims;

/**
 * 过滤各远程请求。
 * @title ProviderAuthenticationFilter
 * @description  对服务调用请求进行身份认证。认证不通过的request禁止访问. 
 * @author hanzhiwei
 * @date 2018年9月13日
 */
@Component("providerAuthenticationFilter")
public class ProviderAuthenticationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderAuthenticationFilter.class);

    @Value("${credit.jwt.secret}")
    private String secret;

    /**
     * 白名单
     */
    private static List<String> whiteList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList = WhiteListUtil.getWhiteList(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        uri = StringUtils.removeStart(uri, req.getContextPath());
        // 判断是否在白名单中
        if (WhiteListUtil.inWhiteList(uri, whiteList)) {
            chain.doFilter(request, response);
            return;
        }

        // 提取认证jwt信息
        Claims claims = WebUtils.fetchClaims(req, secret);
        LOGGER.debug("claims info:{}", claims);

        // 非法jwt
        if (claims == null) {
            LOGGER.error("token is empty");
            this.onInvalid(res, "token invalid");
            return;
        }

        String principal = WebUtils.getValueFromHeader(req, SecurityConsts.HEADER_PRINCIPAL);
        if (!claims.get(SecurityConsts.TOKEN_USER, String.class).equals(principal)) {
            LOGGER.error("user principal is different with token");
            this.onInvalid(res, "user principal is different with token");
            return;
        }

        LOGGER.debug("{} request rpc:{}", claims, req.getRequestURI());
        chain.doFilter(request, response);
    }

    private void onInvalid(HttpServletResponse res, String msg) throws IOException {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ResultInfo<Void> result = new ResultInfo<Void>(Consts.RESULT_CODE_INVALIDTOKEN, msg);
        WebUtils.printToJson(JsonUtils.toJson(result), res);
    }

    @Override
    public void destroy() {
    }

}
