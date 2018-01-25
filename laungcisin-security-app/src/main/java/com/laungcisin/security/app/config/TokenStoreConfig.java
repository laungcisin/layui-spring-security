package com.laungcisin.security.app.config;

import com.laungcisin.security.app.jwt.ImoocJwtTokenEnhancer;
import com.laungcisin.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 令牌存储配置
 */
@Configuration
public class TokenStoreConfig {

    /**
     * redis 配置
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * matchIfMissing = true， 如果配置文件没有改配置，默认生效，除非有配置其它值。
     * jwt配置
     */
    @Configuration
    @ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {
        @Autowired
        private SecurityProperties securityProperties;

        /**
         * 负责 token 存储，不关心 token 生成， token 由 JwtAccessTokenConverter 负责生成。
         * @return
         */
        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * JwtAccessTokenConverter 负责生成 token 生成
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());//签名用的密钥
            return accessTokenConverter;
        }

        /**
         * token 增强器
         * ConditionalOnMissingBean 注解--业务系统可以加入自定义增强器替换系统默认的增强器
         * @return
         */
        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            return new ImoocJwtTokenEnhancer();
        }

    }

}
