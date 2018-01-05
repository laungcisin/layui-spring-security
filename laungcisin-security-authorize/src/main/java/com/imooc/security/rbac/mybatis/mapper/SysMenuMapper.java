package com.imooc.security.rbac.mybatis.mapper;

import com.imooc.security.rbac.mybatis.entity.SysMenu;
import com.imooc.security.rbac.sql.provider.SysMenuSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SysMenuMapper {

    @SelectProvider(type = SysMenuSqlProvider.class, method = "getAllMenuByUsername")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "perms", column = "perms"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    List<SysMenu> getAllMenuByUsername(@Param("username") String username);

    @SelectProvider(type = SysMenuSqlProvider.class, method = "getAllMenuByUserId")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "perms", column = "perms"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    List<SysMenu> getAllMenuByUserId(@Param("userId") Long userId);
}
