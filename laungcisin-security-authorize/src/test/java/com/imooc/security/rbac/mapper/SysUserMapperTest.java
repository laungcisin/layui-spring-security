
package com.imooc.security.rbac.mapper;

import com.imooc.security.rbac.mybatis.entity.SysUser;
import com.imooc.security.rbac.mybatis.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void findUserByUsername() {
        SysUser sysUser = sysUserMapper.getUserByUsername("admin1");
        System.out.println(sysUser);
    }

}