package com.imooc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author laungcisin
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String index(HttpServletRequest request, HttpServletResponse response) {
        logger.info("访问首页");

        return "index";
    }
}
