package com.laungcisin.security.core.authentication;

import com.laungcisin.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author laungcisin
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler laungcisinAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler laungcisinAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()//表单登录
                //SecurityConstants.DEFAULT_UNAUTHENTICATION_URL 此url的处理由 BrowserSecurityController.requireAuthentication方法处理
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)// 指定登录页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//UsernamePasswordAuthenticationFilter处理此url
                .successHandler(laungcisinAuthenticationSuccessHandler)
                .failureHandler(laungcisinAuthenticationFailureHandler)
                .and().headers().frameOptions().disable()// 解决IFrame拒绝的问题
        ;
    }

}
