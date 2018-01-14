package com.laungcisin.security.rbac.mybatis.entity;

import java.io.Serializable;

public class SysRoleMenu implements Serializable {
    private Long id;

    private Long menuId;

    private Long roleId;

    public SysRoleMenu(Long menuId, Long roleId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}