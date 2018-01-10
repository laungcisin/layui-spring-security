package com.laungcisin.service.role;

import com.imooc.security.rbac.mybatis.entity.SysRole;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.PageDataRequestParam;
import com.laungcisin.utils.role.RoleRequestParam;

public interface RoleService {
    PageData getRoleList(RoleRequestParam param);

    void save(RoleRequestParam param);

    SysRole getRoleByRoleId(Long roleId);

    int update(RoleRequestParam param);
}
