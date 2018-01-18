package com.laungcisin.security.core.validate.code.generator;

import com.laungcisin.security.core.validate.code.bean.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author laungcisin
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);

}
