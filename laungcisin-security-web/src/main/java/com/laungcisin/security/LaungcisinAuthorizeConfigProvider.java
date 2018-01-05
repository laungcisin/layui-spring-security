package com.laungcisin.security;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MAX_VALUE)
public class LaungcisinAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "**/favicon.ico",
                "/busi-js/**",
                "/css/**",
                "/datas/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/lib/**",
                "/noPermission/**"
        ).permitAll()
                .anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }
}
