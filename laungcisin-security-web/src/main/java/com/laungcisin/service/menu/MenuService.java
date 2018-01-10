package com.laungcisin.service.menu;

import com.imooc.security.rbac.mybatis.entity.SysMenu;
import com.laungcisin.bean.ZtreeBean;

import java.util.List;

public interface MenuService {
    List<ZtreeBean> getAllMenuByUserId(Long userId);

    List<ZtreeBean> getMenuByRelRoleId(Long roleId);
}
