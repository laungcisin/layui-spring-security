package com.laungcisin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Session Controller
 *
 * @author laungcisin
 */
@Controller
public class SessionController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/session/invalid")
    public String invalid() {
        logger.info("Session失效页");
        return "session/invalid";
    }
}
