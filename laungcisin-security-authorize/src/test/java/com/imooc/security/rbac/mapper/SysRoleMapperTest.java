
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

}