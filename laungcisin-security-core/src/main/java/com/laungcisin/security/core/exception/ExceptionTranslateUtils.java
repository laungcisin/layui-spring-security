package com.laungcisin.security.core.exception;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 异常翻译类
 */
public class ExceptionTranslateUtils {
    
    public static String translate(AuthenticationException exception) {
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

        return message;
    }
}
