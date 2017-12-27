package com.imooc;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * springboot 启动类
 * @author imooc
 */
@SpringBootApplication
@EnableSwagger2
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);// 关闭banner
        springApplication.run(args);
    }

}
