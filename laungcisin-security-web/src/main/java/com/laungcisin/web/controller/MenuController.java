package com.laungcisin.web.controller;

import com.laungcisin.service.menu.MenuService;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.menu.MenuRequestParam;
import com.laungcisin.utils.role.RoleRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "menuService")
    private MenuService menuService;

    @RequestMapping("/listMenuPage")
    public String listMenuPage() {
        logger.info("菜单列表页");
        return "/menu/listMenuPage";
    }

    @RequestMapping("/listMenu")
    @ResponseBody
    public PageData listMenu(MenuRequestParam param) {
        logger.info("菜单列表数据");
        return menuService.getMenuList(param);
    }
}
