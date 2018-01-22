package com.laungcisin.web.controller;

import com.laungcisin.bean.ZtreeBean;
import com.laungcisin.security.LaungcisinSocialUser;
import com.laungcisin.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(getClass());


}
