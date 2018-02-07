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
 * 短信验证码配置类
 * 将SmsCodeAuthenticationFilter、SmsCodeAuthenticationProvider、SmsCodeAuthenticationToken串接起来
 * 放在core模块下,手机端和浏览器端都可以使用
 *
 * @author laungcisin
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // 由具体的子项目中定义 SuccessHandler 和 FailureHandler
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //1.先配置 SmsCodeAuthenticationFilter，SmsCodeAuthenticationFilter 相当于 UsernamePasswordAuthenticationFilter
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();

        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));//加入 AuthenticationManager
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        //2.配置 SmsCodeAuthenticationProvider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        /*
        3.将 SmsCodeAuthenticationProvider 放入到 AuthenticationManager 管理的 Provider 集合中。
        SmsCodeAuthenticationFilter 加入过滤器链中，顺序无所谓
        SpringSecurity 是根据 AuthenticationToken 类型来选择相应的 Provider 来认证
         */
        http.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
