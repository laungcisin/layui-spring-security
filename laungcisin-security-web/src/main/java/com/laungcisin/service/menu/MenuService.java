package com.laungcisin.service.menu;

import com.laungcisin.bean.ZtreeBean;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.menu.MenuRequestParam;
import com.laungcisin.utils.role.RoleRequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MenuService {
    List<ZtreeBean> getAllMenuByUserId(Long userId);

    List<ZtreeBean> getMenuByRelRoleId(Long roleId);

    PageData getMenuList(MenuRequestParam param);
}
