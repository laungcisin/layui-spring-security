package com.imooc.security.rbac.mybatis.mapper;

import com.imooc.security.rbac.mybatis.entity.SysUser;
import com.imooc.security.rbac.mybatis.provider.SysUserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {
    @Select("select * from sys_user where username = #{username} limit 1")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "status", column = "status"),
            @Result(property = "createUserId", column = "create_user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "orgId", column = "org_id"),
    })
    SysUser getUserByUsername(@Param("username") String username);

    @SelectProvider(type = SysUserSqlProvider.class, method = "getUserPageData")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "status", column = "status"),
            @Result(property = "createUserId", column = "create_user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "orgId", column = "org_id"),
    })
    List<SysUser> getUserPageData(@Param("page") Integer page, @Param("limit")Integer limit);

    @Select("select count(*) from sys_user ")
    Integer getAllUserCount();
}
