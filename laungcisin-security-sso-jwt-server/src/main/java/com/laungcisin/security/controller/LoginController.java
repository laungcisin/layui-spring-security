package com.laungcisin.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录 Controller
 *
 * @author laungcisin
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/login")
    public String login() {
        logger.info("访问登录页");
        return "login";
    }
}
