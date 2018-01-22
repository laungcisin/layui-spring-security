package com.laungcisin.security.app;

import com.laungcisin.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.laungcisin.security.core.authorize.AuthorizeConfigManager;
import com.laungcisin.security.core.properties.SecurityProperties;
import com.laungcisin.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class LaungcisinResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer imoocSocialSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.apply(validateCodeSecurityConfig)//验证码校验配置[图形|短信]
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)//短信验证码登录配置
                .and()
                .apply(imoocSocialSecurityConfig)//第三方账号登录
                .and()

                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }
}
