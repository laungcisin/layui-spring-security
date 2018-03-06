package com.laungcisin.security.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laungcisin.security.core.support.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper;

    private String msg = "无权访问";

    public RestAuthenticationAccessDeniedHandler(ObjectMapper objectMapper, String msg) {
        this.objectMapper = objectMapper;
        this.msg = msg;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(HttpStatus.FORBIDDEN.value(), msg)));
    }

}