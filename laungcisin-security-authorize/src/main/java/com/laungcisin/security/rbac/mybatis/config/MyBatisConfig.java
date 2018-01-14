package com.laungcisin.security.rbac.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.laungcisin.security.rbac.mybatis.mapper")
public class MyBatisConfig {
}