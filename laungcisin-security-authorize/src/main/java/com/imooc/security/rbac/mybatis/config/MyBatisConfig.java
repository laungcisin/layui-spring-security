package com.imooc.security.rbac.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.imooc.security.rbac.mybatis.mapper")
public class MyBatisConfig {
}