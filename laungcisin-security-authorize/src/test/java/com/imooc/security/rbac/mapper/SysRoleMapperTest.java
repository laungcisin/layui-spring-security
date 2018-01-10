
package com.imooc.security.rbac.mapper;

import com.imooc.security.rbac.mybatis.entity.SysRole;
import com.imooc.security.rbac.mybatis.mapper.SysRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void getRoleListByUsername() {
        List<SysRole> list = sysRoleMapper.getRoleListByUserId(1L);
        for (SysRole sysRole : list) {
            System.out.println(sysRole);
        }
    }

    @Test
    public void getRolePageData() {
        List<SysRole> list = sysRoleMapper.getRolePageData(1, 10, "管理");
        System.out.println(list);
    }

    @Test
    public void getAllRoleCount() {
        Integer count = sysRoleMapper.getAllRoleCount();
        System.out.println(count);
    }

    @Test
    public void getRoleByRoleId() {
        SysRole sysRole = sysRoleMapper.getRoleByRoleId(21L);
        System.out.println(sysRole);
    }

    @Test
    public void update() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(21L);
        sysRole.setRoleName("管理员21");
        sysRole.setRoleCode("ADMIN21");
        sysRole.setRemark("ADMIN21");
        int result = sysRoleMapper.update(sysRole);
        System.out.println(result);
    }

}