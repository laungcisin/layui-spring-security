package com.imooc.security.rbac.mybatis.provider;

import org.apache.ibatis.annotations.Param;

public class SysMenuSqlProvider {

    public String getAllMenuByUsername(@Param("username") String username) {
        return " select m.* " +
                "	 from sys_user u, sys_user_role ur, sys_role r, sys_role_menu rm, sys_menu m " +
                "	 where u.user_id = ur.user_id  " +
                "	 and ur.role_id = r.role_id  " +
                "	 and r.role_id = rm.role_id  " +
                "	 and rm.menu_id = m.menu_id  " +
                "	 and u.username = #{username} " +
                "	 order by m.menu_id, m.show_sort ";
    }

    public String getAllMenuByUserId(@Param("userId") Long userId) {
        return " select m.* " +
                "	 from sys_user u, sys_user_role ur, sys_role r, sys_role_menu rm, sys_menu m " +
                "	 where u.user_id = ur.user_id  " +
                "	 and ur.role_id = r.role_id  " +
                "	 and r.role_id = rm.role_id  " +
                "	 and rm.menu_id = m.menu_id  " +
                "	 and u.user_id = #{userId} " +
                "	 order by m.menu_id, m.show_sort ";
    }

    public String getMenuTreeByUserId(@Param("userId") Long userId) {
        return " select m.* " +
                "	 from sys_user u, sys_user_role ur, sys_role r, sys_role_menu rm, sys_menu m " +
                "	 where u.user_id = ur.user_id  " +
                "	 and ur.role_id = r.role_id  " +
                "	 and r.role_id = rm.role_id  " +
                "	 and rm.menu_id = m.menu_id  " +
                "	 and u.user_id = #{userId} " +
                "	 order by m.menu_id, m.show_sort ";
    }

    public String getMenuByRelRoleId(@Param("roleId") Long roleId) {
        return "select m.* " +
                "   from sys_role r, sys_role_menu rm, sys_menu m " +
                "   where r.role_id = rm.role_id " +
                "   and rm.menu_id = m.menu_id " +
                "   and r.role_id = #{roleId} " +
                "   order by m.menu_id, m.show_sort ";
    }

}
