package com.laungcisin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 退出 Controller
 *
 * @author imooc
 */
@Controller
public class LogoutController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/logout")
    public String index() {
        logger.info("访问退出页");

        return "logout";
    }
}
