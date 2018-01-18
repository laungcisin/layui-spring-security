package com.laungcisin.security.core.validate.code.config;

import com.laungcisin.security.core.properties.SecurityProperties;
import com.laungcisin.security.core.validate.code.generator.ValidateCodeGenerator;
import com.laungcisin.security.core.validate.code.image.ImageCodeGenerator;
import com.laungcisin.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.laungcisin.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码生成逻辑可配置
 * @author laungcisin
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
