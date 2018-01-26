package com.laungcisin.security.app.config;

import com.laungcisin.security.core.properties.OAuth2ClientProperties;
import com.laungcisin.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * EnableAuthorizationServer注解将类标记为认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class LaungcisinAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired(required = false)
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);

        // 2. JWT配置
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);

            endpoints
                    .tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }

        // 2. 如果是JDBC配置，设置clientDetailsService
        if (tokenStore instanceof JdbcTokenStore) {
            endpoints.setClientDetailsService(clientDetailsService);
        }
    }

    /**
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //不是JDBC配置的话，要设置clients信息
        if (!(tokenStore instanceof JdbcTokenStore)) {
            InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
            if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
                for (OAuth2ClientProperties clientProperties : securityProperties.getOauth2().getClients()) {
                    builder.withClient(clientProperties.getClientId())
                            .secret(clientProperties.getClientSecret())
                            .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                            .accessTokenValiditySeconds(clientProperties.getAccessTokenValiditySeconds())//令牌有效时间
                            .refreshTokenValiditySeconds(2592000)
                            .scopes("all", "write", "read");
                }
            }
        }
    }
}
