package com.laungcisin.security.config;

import com.laungcisin.security.jwt.LaungcisinJwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * 令牌存储配置
 */
@Configuration
public class TokenStoreConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * JWT 配置
     * 如果使用 JWTTokenStore，资源服务器需要一个解码 Token 令牌的类 JwtAccessTokenConverter，
     * JwtTokenStore 依赖这个类进行编码以及解码，授权服务、资源服务都需要配置这个转换类。
     * matchIfMissing = true， 如果配置文件没有该配置，默认生效，除非有配置其它值。
     */
    @Configuration
    @ConditionalOnProperty(prefix = "laungcisin.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {
        @Autowired
        private SecurityProperties securityProperties;

        @Autowired
        private KeyProperties keyProperties;

        /**
         * JwtTokenStore 依赖 JwtAccessTokenConverter 进行编码以及解码 Token。
         *
         * @return
         */
        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * JwtAccessTokenConverter 负责编码及解码 Token 。
         *
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//            accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());//签名用的密钥
            KeyPair keyPair = new KeyStoreKeyFactory
                    (keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getSecret().toCharArray())
                    .getKeyPair(keyProperties.getKeyStore().getAlias());
            accessTokenConverter.setKeyPair(keyPair);
            return accessTokenConverter;
        }

        /**
         * token 增强器
         * ConditionalOnMissingBean 注解--业务系统可以加入自定义增强器替换系统默认的增强器
         *
         * @return
         */
        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            return new LaungcisinJwtTokenEnhancer();
        }

    }

}
