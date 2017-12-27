package com.imooc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录 Controller
 *
 * @author imooc
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String index(HttpServletRequest request, HttpServletResponse response) {
        logger.info("访问登录页");

        return "login";
    }
}
