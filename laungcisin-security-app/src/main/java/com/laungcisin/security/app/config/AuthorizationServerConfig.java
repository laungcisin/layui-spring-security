package com.laungcisin.security.app.config;

import com.laungcisin.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 @EnableAuthorizationServer 来配置授权服务机制，
 * 并继承 AuthorizationServerConfigurerAdapter 类，重写 configure 方法定义授权服务器策略
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //认证管理器，当你选择了密码授权类型时，需注入一个 AuthenticationManager 对象
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

    @Autowired
    private DataSource dataSource;

    /**
     * 授权是使用 AuthorizationEndpoint 这个端点来进行控制的，
     * 使用 AuthorizationServerEndpointsConfigurer来声明授权和token的端点以及token的服务的一些配置信息，
     * 比如采用什么存储方式、token的有效期等
     *
     * @param endpoints
     * @throws Exception
     */
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
    }

    /**
     * 配置客户端详情（ClientDetails）.
     * client的信息的读取.
     * jdbc方式需要调用 JdbcClientDetailsService 类.
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //不是JDBC配置的话，要设置clients信息
        //FIXME:可以改成都从数据库中读取clients信息
        if (!(tokenStore instanceof JdbcTokenStore)) {
            InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//            if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
//                for (OAuth2ClientProperties clientProperties : securityProperties.getOauth2().getClients()) {
//                    builder.withClient(clientProperties.getClientId())
//                            .secret(clientProperties.getClientSecret())
//                            .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                            .accessTokenValiditySeconds(clientProperties.getAccessTokenValiditySeconds())//令牌有效时间
//                            .refreshTokenValiditySeconds(2592000)
//                            .scopes("all", "write", "read");
//                }
//            }

            JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
            List<ClientDetails> clientDetailsList = jdbcClientDetailsService.listClientDetails();
            if (!CollectionUtils.isEmpty(clientDetailsList)) {
                for (ClientDetails clientDetails : clientDetailsList) {
                    builder.withClient(clientDetails.getClientId())
                            .secret(clientDetails.getClientSecret())
                            .authorizedGrantTypes(clientDetails.getAuthorizedGrantTypes().toArray(new String[]{}))
                            .accessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds())
                            .refreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds())
                            .scopes(clientDetails.getScope().toArray(new String[]{}));
                }
            }
        } else {
            clients.withClientDetails(clientDetailsService);
        }
    }

    /**
     * /oauth/token_key 用于获取签名使用的 signingKey，
     * 默认 /oauth/token_key 的访问安全规则是 "denyAll()" 即关闭的，
     * 可以注入一个标准的 SpringEL 表达式到 AuthorizationServerSecurityConfigurer 配置类中将它开启，
     * 例如 permitAll() 或者 isAuthenticated()
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");//经过认证后，才可以访问 /oauth/token_key
    }
}
