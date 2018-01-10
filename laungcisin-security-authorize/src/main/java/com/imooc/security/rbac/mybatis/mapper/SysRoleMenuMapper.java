package com.imooc.security.rbac.mybatis.mapper;

import com.imooc.security.rbac.mybatis.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;


public interface SysRoleMenuMapper {
    @Insert("INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES (#{sysRoleMenu.menuId}, #{sysRoleMenu.roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "sysRoleMenu.id")
    void save(@Param("sysRoleMenu") SysRoleMenu sysRoleMenu);
}
