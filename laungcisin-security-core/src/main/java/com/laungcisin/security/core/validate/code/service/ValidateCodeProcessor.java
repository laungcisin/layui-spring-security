package com.laungcisin.security.core.validate.code.service;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 处理验证码整个流程:
 *      生成(create)和校验(validate)
 *      生成（create）包含：generate、save、send等步骤
 * 校验码处理器，封装不同校验码的处理逻辑
 *
 * @author laungcisin
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);

}
