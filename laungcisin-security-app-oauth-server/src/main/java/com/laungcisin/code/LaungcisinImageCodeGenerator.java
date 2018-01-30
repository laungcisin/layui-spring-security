package com.laungcisin.code;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.laungcisin.security.core.properties.SecurityProperties;
import com.laungcisin.security.core.validate.code.generator.ValidateCodeGenerator;
import com.laungcisin.security.core.validate.code.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * 图形验证码生成器
 * @author laungcisn
 */
@Component("imageValidateCodeGenerator")
public class LaungcisinImageCodeGenerator implements ValidateCodeGenerator {
    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private Producer producer;

    @Bean
    public DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Override
    public ImageCode generate(ServletWebRequest request) {
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        return new ImageCode(image, text, securityProperties.getCode().getImage().getExpireIn());
    }

}
