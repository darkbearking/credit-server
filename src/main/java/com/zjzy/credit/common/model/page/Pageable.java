package com.zjzy.credit.common.model.page;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @title Pageable
 * @description 可分页的注解. 表示相关method需要进行分页处理.
 * @author zhiwei.han
 * @date 2019年8月12日
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pageable {

}
