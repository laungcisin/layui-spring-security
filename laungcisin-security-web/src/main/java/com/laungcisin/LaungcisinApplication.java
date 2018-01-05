package com.laungcisin;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.laungcisin", "com.imooc"})
@EnableSwagger2
public class LaungcisinApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaungcisinApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);// 关闭banner
        springApplication.run(args);
    }
}
