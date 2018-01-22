package com.laungcisin.web.controller;

import com.laungcisin.security.rbac.mybatis.entity.SysMenu;
import com.laungcisin.security.rbac.mybatis.mapper.SysMenuMapper;
import com.laungcisin.security.rbac.utils.TreeBuilder;
import com.laungcisin.bean.ZtreeBean;
import com.laungcisin.security.LaungcisinSocialUser;
import com.laungcisin.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController()
public class NoPermissionController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Resource(name = "menuService")
    private MenuService menuService;

    @GetMapping("/noPermission/treeData")
    public List<ZtreeBean> listRole(@AuthenticationPrincipal UserDetails user) {
        logger.info("NoPermissionController.listRole()列表数据");
        LaungcisinSocialUser laungcisinSocialUser = (LaungcisinSocialUser) user;
        return menuService.getAllMenuByUserId(laungcisinSocialUser.getId());
    }

    @GetMapping("/noPermission/menuList")
    public List<TreeBuilder.Node> menuList(@AuthenticationPrincipal UserDetails user) {
        logger.info("NoPermissionController.menuList()列表数据");
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

        return new ArrayList<>();
    }
}
