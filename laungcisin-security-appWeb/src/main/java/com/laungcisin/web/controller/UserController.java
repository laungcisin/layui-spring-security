package com.laungcisin.web.controller;

import com.laungcisin.security.core.properties.SecurityProperties;
import com.laungcisin.service.user.UserService;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.PageDataRequestParam;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

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

    @RequestMapping("/me")
    @ResponseBody
    public Object me(Authentication user, HttpServletRequest request) throws Exception {
        logger.info("登录用户");
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearea ");

        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");
        logger.info("token附加信息：{}", company);
        return user;
    }
}
