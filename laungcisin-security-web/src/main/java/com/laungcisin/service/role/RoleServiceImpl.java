package com.laungcisin.service.role;

import com.laungcisin.security.rbac.mybatis.entity.SysRole;
import com.laungcisin.security.rbac.mybatis.entity.SysRoleMenu;
import com.laungcisin.security.rbac.mybatis.mapper.SysRoleMapper;
import com.laungcisin.security.rbac.mybatis.mapper.SysRoleMenuMapper;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.role.RoleRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public PageData getRoleList(RoleRequestParam param) {
        PageData pageData = new PageData();
        try {
            pageData.setCount(sysRoleMapper.getAllRoleCount());
            List<SysRole> list = sysRoleMapper.getRolePageData(param.getPage(), param.getLimit(), param.getRoleName());
            pageData.setData(list);
        } catch (Exception e) {
            pageData.setCode(200);
            pageData.setMsg("查询数据出错");
            logger.error("RoleServiceImpl.getRoleList()查询数据出错");
        }

        return pageData;
    }

    @Transactional
    @Override
    public void save(RoleRequestParam param) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(param, sysRole);
        sysRole.setStatus((short) 1);

        sysRoleMapper.save(sysRole);
        Long roleId = sysRole.getRoleId();
        List<Long> menuIdList = param.getMenuIdList();

        for (Long menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu(menuId, roleId);
            sysRoleMenuMapper.save(sysRoleMenu);
        }

    }

    @Override
    public SysRole getRoleByRoleId(Long roleId) {
        return sysRoleMapper.getRoleByRoleId(roleId);
    }

    @Override
    public int update(RoleRequestParam param) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(param, sysRole);
        return sysRoleMapper.update(sysRole);
    }
}
