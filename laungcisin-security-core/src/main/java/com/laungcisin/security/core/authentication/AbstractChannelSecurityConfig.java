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
    protected AuthenticationSuccessHandler successHandler;

    @Autowired
    protected AuthenticationFailureHandler failureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()//使用表单登录，不再使用默认httpBasic方式
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)// 指定登录页面

                /*
                如果请求的URL需要认证，则跳转到loginProcessingUrl指定的url进行认证
                    SecurityConstants.DEFAULT_UNAUTHENTICATION_URL定义的url，
                    由 BrowserSecurityController.requireAuthentication方法处理
                 */
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//需要认证时，跳转到此URL
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().headers().frameOptions().disable()// 解决IFrame拒绝的问题
        ;
    }

}
