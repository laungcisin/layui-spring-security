package com.laungcisin.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 完成短信登录功能
 * 放在core模块下,手机端和浏览器端都可以使用
 * 短信验证码配置类-
 *  连接SmsCodeAuthenticationFilter|SmsCodeAuthenticationProvider|SmsCodeAuthenticationToken类
 * @author laungcisin
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler laungcisinAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler laungcisinAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //1.先配置SmsCodeAuthenticationFilter，SmsCodeAuthenticationFilter相当于UsernamePasswordAuthenticationFilter
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        //加入AuthenticationManager
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(laungcisinAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(laungcisinAuthenticationFailureHandler);

        //2.配置SmsCodeAuthenticationProvider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        //3.将SmsCodeAuthenticationFilter和SmsCodeAuthenticationProvider加入到SpringSecurity框架中
        // 将此 SmsCodeAuthenticationFilter 加在 UsernamePasswordAuthenticationFilter后，顺序无所谓
        // SpringSecurity是根据AuthenticationToken类型来选择相应的Provider来认证
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
