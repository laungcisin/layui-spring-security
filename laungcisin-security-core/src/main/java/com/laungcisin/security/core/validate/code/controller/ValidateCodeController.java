package com.laungcisin.security.core.validate.code.controller;

import com.laungcisin.security.core.properties.SecurityConstants;
import com.laungcisin.security.core.support.SimpleResponse;
import com.laungcisin.security.core.validate.code.service.ValidateCodeProcessor;
import com.laungcisin.security.core.validate.code.service.ValidateCodeProcessorHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验码生成 Controller
 *
 * @author laungcisin
 */
@RestController
public class ValidateCodeController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 生成验证码，
     * 根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public SimpleResponse createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {

        try {
            validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
        }catch (Exception e) {
            logger.error("获取验证码失败");
            return new SimpleResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取验证码失败");
        }

        return new SimpleResponse(HttpStatus.OK.value(), "验证码发送成功");
    }

}
