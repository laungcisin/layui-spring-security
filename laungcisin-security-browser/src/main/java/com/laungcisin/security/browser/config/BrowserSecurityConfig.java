package com.laungcisin.security.browser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laungcisin.security.browser.handler.RestAuthenticationAccessDeniedHandler;
import com.laungcisin.security.core.authentication.AbstractChannelSecurityConfig;
import com.laungcisin.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.laungcisin.security.core.authorize.AuthorizeConfigManager;
import com.laungcisin.security.core.properties.SecurityProperties;
import com.laungcisin.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 浏览器相关配置类
 *
 * @author laungcisin
 */
@Configuration//通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Resource(name = "validateCodeSecurityConfig")
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${authorization.failed.msg}")
    private String authorizationFailedMsg;

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new RestAuthenticationAccessDeniedHandler(objectMapper, authorizationFailedMsg);
    }

    /**
     * 记住我功能-数据库存储相关cookies
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)//验证码校验配置[图形|短信]--在认证Filter前先做校验
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)//短信登录配置
                .and()
                .exceptionHandling().accessDeniedHandler(getAccessDeniedHandler())
                .and()

                //记住我功能
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())//过期秒数
                .userDetailsService(userDetailsService)//获取到用户名后，用userDetailsService做登录
                .and()

                //退出功能
                .logout()
                .logoutUrl("/logout")//自定义退出的地址
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")//删除当前的JSESSIONID
                .and()

                //session相关配置
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())//最大Session数量---并发控制
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()

                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
