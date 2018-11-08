package com.laungcisin.web.controller;

import com.laungcisin.security.LaungcisinSocialUser;
import com.laungcisin.security.rbac.mybatis.entity.SysRole;
import com.laungcisin.bean.ZtreeBean;
import com.laungcisin.service.menu.MenuService;
import com.laungcisin.service.role.RoleService;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.R;
import com.laungcisin.utils.role.RoleRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "menuService")
    private MenuService menuService;

    @RequestMapping("/listRolePage")
    public String listRolePage() {
        logger.info("角色列表页");
        return "/role/listRolePage";
    }

    @RequestMapping("/addRolePage")
    public String addRolePage() {
        logger.info("添加角色页");
        return "/role/addRolePage";
    }

    @RequestMapping("/listRole")
    @ResponseBody
    public PageData listRole(RoleRequestParam param) {
        logger.info("角色列表数据");
        return roleService.getRoleList(param);
    }

    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody RoleRequestParam param, @AuthenticationPrincipal UserDetails user) {
        logger.info("保存角色数据");
        try {
            LaungcisinSocialUser laungcisinSocialUser = (LaungcisinSocialUser) user;
            param.setCreateUserId(laungcisinSocialUser.getUserId());
            param.setCreateTime(new Date());
            roleService.save(param);
        } catch (Exception e) {
            return R.error("保存出错！");
        }
        return R.ok("保存成功！");
    }

    @RequestMapping("/updateRolePage/{roleId}")
    public String updateRolePage(Model model, @PathVariable("roleId") Long roleId) {
        logger.info("修改角色页");
        SysRole role = roleService.getRoleByRoleId(roleId);
        model.addAttribute("role", role);
        return "/role/updateRolePage";
    }

    /**
     * 根据roleId获取sys_role_menu表中所拥有的role及menu数据
     */
    @ResponseBody
    @RequestMapping("/info/{roleId}")
    public List<ZtreeBean> info(@PathVariable("roleId") Long roleId) {
        List<ZtreeBean> menuList = menuService.getMenuByRelRoleId(roleId);
        return menuList;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public R update(@RequestBody RoleRequestParam param) {
        logger.info("更新角色数据");
        try {
            roleService.update(param);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return R.error("更新出错！");
        }
        return R.ok("更新成功！");
    }
}
