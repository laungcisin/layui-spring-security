package com.laungcisin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 首页
 * @author laungcisin
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        logger.info("访问首页");
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        logger.info("访问欢迎页");
        return "welcome";
    }

}
