package com.laungcisin.security.rbac.mybatis.mapper;

import com.laungcisin.security.rbac.mybatis.entity.SysMenu;
import com.laungcisin.security.rbac.mybatis.provider.SysMenuSqlProvider;
import com.laungcisin.security.rbac.mybatis.provider.SysRoleSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysMenuMapper {

    @Select("select * from sys_menu where id = #{id}")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "permissions", column = "permissions"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    SysMenu getMenuById(@Param("id") String id);

    @SelectProvider(type = SysMenuSqlProvider.class, method = "getAllMenuByUsername")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "permissions", column = "permissions"),
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
            @Result(property = "permissions", column = "permissions"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    List<SysMenu> getAllMenuByUserId(@Param("userId") Long userId);

    @SelectProvider(type = SysMenuSqlProvider.class, method = "getMenuTreeByUserId")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "permissions", column = "permissions"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    List<SysMenu> getMenuTreeByUserId(@Param("userId") Long userId);

    @Select("select * from sys_menu where FIND_IN_SET(menu_id, getChildrenList(#{parentId}))")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "permissions", column = "permissions"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    List<SysMenu> getAllSubMenuByParentId(@Param("parentId") Long parentId);


    @SelectProvider(type = SysMenuSqlProvider.class, method = "getMenuByRelRoleId")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "permissions", column = "permissions"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
    })
    List<SysMenu> getMenuByRelRoleId(@Param("roleId") Long roleId);

    @Select("select count(*) from sys_menu ")
    Integer getAllMenuCount();

    @SelectProvider(type = SysMenuSqlProvider.class, method = "getMenuPageData")
    @Results({
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "permissions", column = "permissions"),
            @Result(property = "type", column = "type"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "showSort", column = "show_sort"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "parentName", column = "parent_name"),
    })
    List<SysMenu> getMenuPageData(@Param("page") Integer page, @Param("limit") Integer limit, @Param("menuName") String menuName);
}
