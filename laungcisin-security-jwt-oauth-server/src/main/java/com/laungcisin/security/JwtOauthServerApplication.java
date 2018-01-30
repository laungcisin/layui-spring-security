package com.laungcisin.security;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.laungcisin"}, exclude = OAuth2AutoConfiguration.class)
@EnableTransactionManagement
public class JwtOauthServerApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(JwtOauthServerApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);// 关闭banner
        springApplication.run(args);
    }
}
