package com.laungcisin.web.controller;

import com.imooc.security.rbac.mybatis.entity.SysMenu;
import com.imooc.security.rbac.mybatis.mapper.SysMenuMapper;
import com.imooc.security.rbac.utils.TreeBuilder;
import com.laungcisin.security.LaungcisinSocialUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laungcisin
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        logger.info("访问首页");
        return "index";
    }

    @RequestMapping("/noPermission/welcome")
    public String welcome(HttpServletResponse response) {
        logger.info("访问欢迎页");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题
        return "welcome";
    }

    @GetMapping("/noPermission/menuList")
    @ResponseBody
    public List<TreeBuilder.Node> menuList(HttpServletResponse response, @AuthenticationPrincipal UserDetails user) {
        LaungcisinSocialUser laungcisinSocialUser = (LaungcisinSocialUser) user;
        List<SysMenu> list = sysMenuMapper.getAllMenuByUserId(laungcisinSocialUser.getId());

        if (!CollectionUtils.isEmpty(list)) {
            List<TreeBuilder.Node> treeNode = new ArrayList<>();
            for (SysMenu menu : list) {
                TreeBuilder.Node node = new TreeBuilder.Node();
                node.setId(menu.getMenuId().toString());
                node.setPid(menu.getParentId().toString());
                node.setTitle(menu.getName());
                node.setIcon(menu.getIcon());
                node.setHref(menu.getUrl());
                treeNode.add(node);
            }

            return (new TreeBuilder(treeNode)).buildTree();
        }

        return null;
    }

}
