
package com.laungcisin.security.rbac.mapper;

import com.laungcisin.security.rbac.mybatis.entity.SysRoleMenu;
import com.laungcisin.security.rbac.mybatis.mapper.SysRoleMenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleMenuMapperTest {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Test
    @Transactional
    public void save() {
        for (int i = 0; i < 10; i++) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu(8L, 1L);
            sysRoleMenuMapper.save(sysRoleMenu);
            System.out.println(sysRoleMenu.getId());

            if (i == 5) {
                throw new RuntimeException("运行出错");
            }
        }
    }


}