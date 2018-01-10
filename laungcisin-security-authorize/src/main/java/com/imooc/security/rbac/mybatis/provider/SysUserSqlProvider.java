package com.imooc.security.rbac.mybatis.provider;

import org.apache.ibatis.annotations.Param;

public class SysUserSqlProvider {
    public String getUserPageData(@Param("page") Integer page, @Param("limit")Integer limit) {
        Integer start = (page - 1) * limit;
        Integer end = page * limit;
        return "select * from sys_user where 1 = 1 limit " + start + ", " + end;
    }

}
