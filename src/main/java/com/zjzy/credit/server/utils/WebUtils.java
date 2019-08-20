package com.zjzy.credit.server.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjzy.credit.common.consts.SecurityConsts;
import com.zjzy.credit.common.utils.JWTUtils;
import com.zjzy.credit.server.base.CurrentIdentityInfoHolder;

import io.jsonwebtoken.Claims;

/**
 * @description Web工具类. 
 * @author zhiwei.han
 * @date 2019-08-13 16:19:09
 */
public class WebUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

    /**
     * @param request http请求
     * @param name Cookie里面的key
     * @return 得到 Cookie 里面的name对应的value
     */
    public static String getValueFromCookie(HttpServletRequest request, String name) {
        String val = null;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals(name)) {
                    val = c.getValue();
                    break;
                }
            }
        }

        if (StringUtils.isBlank(val)) {
            // 尝试通过header获取
            String cookieValue = request.getHeader("cookie");
            if (StringUtils.isBlank(cookieValue)) {
                return val;
            }

            String[] cookies = cookieValue.split("; ");
            for (String cookie : cookies) {
                String[] cookieSplit = cookie.split("=");
                if (cookieSplit.length == 2) {
                    String cookieName = cookieSplit[0];
                    if (cookieName.equals(name)) {
                        val = cookieSplit[1];
                        break;
                    }
                }
            }
        }

        return val;
    }

    /**
     * 获取当前登录用户名
     */
    public static String getDefaultUser() {
        return CurrentIdentityInfoHolder.getUserKey();
    }

    /**
     * 将json字符串写入到response.
     * @param json
     * @param response
     * @throws IOException 
     */
    public static void printToJson(String json, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
            response.setDateHeader("Expires", 0);
            out.print(json);
            out.flush();

        } catch (Exception e) {
            LOGGER.error("Error:" + e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 从header中提取jwt字符串.
     * @param request
     * @return
     */
    public static String fetchJWT(HttpServletRequest request) {
        String auth = request.getHeader(SecurityConsts.HEADER_AUTHORIZATION);

        if (StringUtils.isEmpty(auth)) {
            return null;
        }

        return auth.trim();
    }

    /**
     * 获取claims
     * @param request
     * @param secret
     * @return
     */
    public static Claims fetchClaims(HttpServletRequest request, String secret) {
        String jwt = fetchJWT(request);
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }

        // 解析jwt
        Claims claims = JWTUtils.parseJWT(jwt, Base64.encodeBase64String(secret.getBytes()));
        LOGGER.debug("claims info:{}", claims);

        // 非法jwt
        if (claims == null) {
            return null;
        }
        return claims;
    }

    /**
     * 从header中提取value
     * @param request request
     * @param name header key
     * @return 对应的header中的值
     */
    public static String getValueFromHeader(HttpServletRequest request, String name) {
        String auth = request.getHeader(name);

        if (StringUtils.isEmpty(auth)) {
            return null;
        }

        return auth.trim();
    }
}
