package com.laungcisin.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("imooc");
        return accessTokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("laungcisin1")
                .secret("imoocsecret1")
                .authorizedGrantTypes("authorization_code", "refresh_code")
                .scopes("all")
                .autoApprove(true)
                .and()
                .withClient("laungcisin2")
                .secret("imoocsecret2")
                .authorizedGrantTypes("authorization_code", "refresh_code")
                .scopes("all")
                .autoApprove(true);         //登录后绕过批准询问(/oauth/confirm_access)
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
    }

    /**
     * 默认 /oauth/token_key 的访问安全规则是 "denyAll()" 即关闭的，
     * 可以注入一个标准的 SpringEL 表达式到 AuthorizationServerSecurityConfigurer 配置类中将它开启，
     * 例如 permitAll() 或者 isAuthenticated()
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");//经过认证后，才可以访问 /oauth/token_key
    }
}
