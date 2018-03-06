package com.laungcisin.security;

import com.laungcisin.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MAX_VALUE)
public class LaungcisinAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception {
        config.antMatchers(
                "**/favicon.ico",
                "/busi-js/**",
                "/css/**",
                "/datas/**",
                "/error/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/lib/**",
                "/welcome",
                "/noPermission/**"
        ).permitAll().anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }
}
