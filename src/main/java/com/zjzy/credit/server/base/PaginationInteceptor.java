package com.zjzy.credit.server.base;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import com.zjzy.credit.common.model.page.Order;
import com.zjzy.credit.common.model.page.Pagination;
import com.zjzy.credit.common.model.page.Sort;

/**
 * 分页拦截器.
 * @title PaginationInteceptor
 * @description 对系统中需要分页的服务method自动设置分页信息. 
 * @author zhiwei.han
 * @date 2019年8月12日
 */
@Component
@Aspect
public class PaginationInteceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaginationInteceptor.class);

    @SuppressWarnings("rawtypes")
    @Pointcut("execution(public * com.zjzy.credit.server.service..*(..,com.zjzy.credit.common.model.page.Pagination)) && args(..,pagination) && @annotation(com.zjzy.credit.common.model.page.Pageable)")
    public void aspect(Pagination pagination) {

    }

    /**
     * 在调用分页的service method之前对分页信息进行设置.
     * 避免了每个分页方法都进行一次分页处理.
     * @param joinPoint 
     * @param pagination 分页信息
     */
    @Before("aspect(pagination)")
    @SuppressWarnings("rawtypes")
    public void before(JoinPoint joinPoint, Pagination pagination) {
        LOGGER.debug("the target method {} is pagination, do Pagination config.", joinPoint.getSignature());
        Preconditions.checkNotNull(pagination);

        PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
        Sort sort = pagination.getSort();
        if (sort != null) {
            Iterator<Order> it = sort.iterator();
            StringBuilder sb = new StringBuilder();
            while (it.hasNext()) {
                Order order = it.next();
                // 将java命名格式转换为数据库的下划线命名格式
                String underscoreName = underscoreName(order.getProperty());
                sb.append(underscoreName + " " + order.getDirection().name());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
            PageHelper.orderBy(sb.toString());
        }
    }

    private static String underscoreName(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(name.substring(0, 1).toLowerCase());
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            String slc = s.toLowerCase();
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            } else {
                result.append(s);
            }
        }
        return result.toString();
    }
}
