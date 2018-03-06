package com.laungcisin.security.rbac.mybatis.provider;

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

    public String getMenuPageData(@Param("page") Integer page, @Param("limit") Integer limit, @Param("menuName") String menuName) {
        Integer start = (page - 1) * limit;
        Integer end = page * limit;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("	m.*, t.`name` as parent_name ");
        sql.append("from ");
        sql.append("	sys_menu m ");
        sql.append("left join sys_menu t on t.menu_id = m.parent_id ");
        sql.append("where 1 = 1 ");
        sql.append("order by m.show_sort asc");
        sql.append(" limit " + start + ", " + end);

        return sql.toString();
    }

}
