package com.imooc.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private SecurityProperties securityProperties;


    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=UTF-8");

            //根据exception类型,翻译相应的出错信息
            String message;
            if (exception instanceof UsernameNotFoundException) {
                message = "用户名或者密码错误";
            } else if (exception instanceof BadCredentialsException) {
                message = "用户名或者密码错误";
            } else if (exception instanceof LockedException) {
                message = "此账户已被锁定,请联系管理员";
            } else if (exception instanceof DisabledException) {
                message = "无效账户,请联系管理员";
            } else if (exception instanceof AccountExpiredException) {
                message = "账户过期,请联系管理员";
            } else {
                message = exception.getMessage();
            }

            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(HttpStatus.UNAUTHORIZED.value(), message)));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }

}
