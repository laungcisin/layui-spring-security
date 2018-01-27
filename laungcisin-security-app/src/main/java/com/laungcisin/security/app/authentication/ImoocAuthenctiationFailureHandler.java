package com.laungcisin.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laungcisin.security.core.exception.ExceptionTranslateUtils;
import com.laungcisin.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败 handler
 *
 * @author imooc
 */
@Component("imoocAuthenctiationFailureHandler")
public class ImoocAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * app端返回json格式数据
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");

        //根据exception类型,翻译相应的出错信息
        String message = ExceptionTranslateUtils.translate(exception);
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(HttpStatus.UNAUTHORIZED.value(), message)));

    }

}
