package com.laungcisin.security.rbac;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationTest.class);
        springApplication.setBannerMode(Banner.Mode.OFF);// 关闭banner
        springApplication.run(args);
    }
}
