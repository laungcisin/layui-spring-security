
package com.imooc.security.rbac.mapper;

import com.imooc.security.rbac.mybatis.entity.SysUser;
import com.imooc.security.rbac.mybatis.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void getUserByUsername() {
        SysUser sysUser = sysUserMapper.getUserByUsername("admin1");
        System.out.println(sysUser);
    }

    @Test
    public void getAllUserCount() {
        Integer count = sysUserMapper.getAllUserCount();
        System.out.println(count);
    }

    @Test
    public void getUserPageData() {
        List<SysUser> list = sysUserMapper.getUserPageData(1, 10);
        System.out.println(list);
    }
}