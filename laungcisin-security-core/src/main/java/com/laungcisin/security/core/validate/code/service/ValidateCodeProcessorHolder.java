package com.laungcisin.security.core.validate.code.service;

import com.laungcisin.security.core.validate.code.bean.ValidateCodeType;
import com.laungcisin.security.core.validate.code.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 可以理解为service层
 * @author laungcisin
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        // 校验码Processor上Component注解的value为: [type]ValidateCodeProcessor,
        // 所以拼装的name为[type]ValidateCodeProcessor
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
