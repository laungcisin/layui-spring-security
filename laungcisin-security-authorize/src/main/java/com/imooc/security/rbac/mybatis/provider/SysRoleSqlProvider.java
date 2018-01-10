package com.imooc.security.rbac.mybatis.provider;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

public class SysRoleSqlProvider {

    public String getRolePageData(@Param("page") Integer page, @Param("limit") Integer limit, @Param("roleName") String roleName) {
        Integer start = (page - 1) * limit;
        Integer end = page * limit;
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from sys_role where 1 = 1 ");
        if(!StringUtils.isEmpty(roleName)) {
            sql.append(" and role_name like '%" + roleName + "%' ");
        }
        sql.append(" limit " + start + ", " + end);

        return sql.toString();
    }
}
