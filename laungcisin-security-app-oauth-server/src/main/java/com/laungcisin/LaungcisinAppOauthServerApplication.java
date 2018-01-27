package com.laungcisin;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.laungcisin"}, exclude = OAuth2AutoConfiguration.class)
@EnableTransactionManagement
@EnableSwagger2
public class LaungcisinAppOauthServerApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaungcisinAppOauthServerApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);// 关闭banner
        springApplication.run(args);
    }
}
