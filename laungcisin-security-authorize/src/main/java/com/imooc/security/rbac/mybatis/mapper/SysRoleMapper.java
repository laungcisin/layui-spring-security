package com.imooc.security.rbac.mybatis.mapper;

import com.imooc.security.rbac.mybatis.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper {
    @Select("select r.* from sys_user u, sys_user_role ur, sys_role r " +
            "where u.user_id = ur.user_id and ur.role_id = r.role_id and u.user_id = #{userId}")
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleCode", column = "role_code"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createUserId", column = "create_user_id"),
            @Result(property = "createTime", column = "create_time"),
    })
    List<SysRole> getRoleListByUserId(@Param("userId") Long userId);
}
