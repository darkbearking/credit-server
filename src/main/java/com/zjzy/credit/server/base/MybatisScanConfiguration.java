package com.zjzy.credit.server.base;

import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("com.zjzy.credit.server.dao")
public class MybatisScanConfiguration {
}
