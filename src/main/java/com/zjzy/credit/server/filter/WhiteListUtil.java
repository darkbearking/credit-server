package com.zjzy.credit.server.filter;

import java.util.Collections;
import java.util.List;

import javax.servlet.FilterConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import com.google.common.base.Splitter;

/**
 * @description 白名单工具类 
 * @author zhiwei.han
 * @date 2019-08-13 17:14:23
 */
public class WhiteListUtil {

    /**
     * 可以做URLs匹配，规则如下
        ？匹配一个字符 
        *匹配0个或多个字符
        **匹配0个或多个目录
    */
    private static final AntPathMatcher ANTMATCHER = new AntPathMatcher();

    private WhiteListUtil() {
    }

    /**
     * 获取白名单列表
     * @param filterConfig
     * @param name
     * @return
     */
    public static List<String> getWhiteList(FilterConfig filterConfig) {
        String whiteListStr = null;
        String value = filterConfig.getInitParameter("whiteList");
        if (StringUtils.isNotBlank(value)) {
            whiteListStr = value;
        } else {
            whiteListStr = filterConfig.getServletContext().getInitParameter("whiteList");
        }

        if (StringUtils.isBlank(whiteListStr)) {
            return Collections.emptyList();
        }

        return Splitter.on(';').trimResults().omitEmptyStrings().splitToList(whiteListStr);
    }

    /**
     * 判断uri是否在白名单中
    * @Title: inWhiteList 
    * @Description: 判断uri是否在白名单中
    * @param uri 请求uri
    * @param whiteList 白名单列表 
    * @return boolean    返回类型 
    * @throws
     */
    public static boolean inWhiteList(String uri, List<String> whiteList) {
        if (StringUtils.isBlank(uri)) {
            return false;
        }
        for (String wl : whiteList) {
            if (ANTMATCHER.match(wl, uri)) {
                return true;
            }
        }
        return false;
    }

}
