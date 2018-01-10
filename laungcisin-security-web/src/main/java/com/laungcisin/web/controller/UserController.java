package com.laungcisin.web.controller;

import com.laungcisin.service.user.UserService;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.PageDataRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/listUserPage")
    public String listUserPage() {
        logger.info("用户列表页");
        return "/user/listUserPage";
    }

    @RequestMapping("/addUserPage")
    public String addUserPage() {
        logger.info("添加用户页");
        return "/user/addUserPage";
    }

    @RequestMapping("/listUser")
    @ResponseBody
    public PageData listUser(PageDataRequestParam param) {
        logger.info("用户列表数据");
        return userService.getUserList(param);
    }
}
