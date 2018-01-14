package com.laungcisin.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author laungcisin
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);

}
