package com.laungcisin.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 资源服务器
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()//表单登录
                .loginPage("/login")// 自定义登录页
                .permitAll()
                .and()
                .authorizeRequests()
//                .antMatchers(
//                        "/oauth/**", "/oauth/token", "/oauth/check_token", "/oauth/authorize", "/oauth/confirm_access", "/oauth/error"
//                ).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
        ;
    }

}
