package com.imooc.security.rbac.mybatis.mapper;

import com.imooc.security.rbac.mybatis.entity.SysRole;
import com.imooc.security.rbac.mybatis.provider.SysRoleSqlProvider;
import org.apache.ibatis.annotations.*;

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

    @Select("select count(*) from sys_role ")
    Integer getAllRoleCount();

    @Select("select * from sys_role where role_id = #{roleId}")
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleCode", column = "role_code"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createUserId", column = "create_user_id"),
            @Result(property = "createTime", column = "create_time"),
    })
    SysRole getRoleByRoleId(@Param("roleId") Long roleId);

    @SelectProvider(type = SysRoleSqlProvider.class, method = "getRolePageData")
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "roleCode", column = "role_code"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createUserId", column = "create_user_id"),
            @Result(property = "createTime", column = "create_time"),
    })
    List<SysRole> getRolePageData(@Param("page") Integer page, @Param("limit") Integer limit, @Param("roleName") String roleName);

    @Insert("INSERT INTO `sys_role`(`role_name`, `role_code`, `status`, `remark`, `create_user_id`, `create_time`) " +
            "VALUES (#{sysRole.roleName}, #{sysRole.roleCode}, #{sysRole.status}, #{sysRole.remark}, #{sysRole.createUserId}, #{sysRole.createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "sysRole.roleId")
    void save(@Param("sysRole") SysRole SysRole);

    @Update("UPDATE `sys_role` SET `role_name` = #{sysRole.roleName}, `role_code` = #{sysRole.roleCode}, `remark` = #{sysRole.remark} WHERE `role_id` = #{sysRole.roleId}")
    int update(@Param("sysRole") SysRole sysRole);
}
