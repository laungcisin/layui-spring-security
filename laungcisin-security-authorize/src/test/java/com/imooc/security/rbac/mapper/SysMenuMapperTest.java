
package com.imooc.security.rbac.mapper;

import com.imooc.security.rbac.mybatis.entity.SysMenu;
import com.imooc.security.rbac.mybatis.mapper.SysMenuMapper;
import com.imooc.security.rbac.utils.TreeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuMapperTest {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    public void getAllMenuByUserId() {
        List<SysMenu> list = sysMenuMapper.getAllMenuByUserId(1L);

        List<TreeBuilder.Node> treeNode = new ArrayList<>();
        for (SysMenu menu : list) {
            TreeBuilder.Node node = new TreeBuilder.Node();
            node.setId(menu.getMenuId().toString());
            node.setPid(menu.getParentId().toString());
            node.setTitle(menu.getName());
            treeNode.add(node);
        }

        TreeBuilder treeBuilder = new TreeBuilder(treeNode);
        System.out.println(treeBuilder.buildJSONTree());
    }

    @Test
    public void getAllSubMenuByParentId() {
        List<SysMenu> list = sysMenuMapper.getAllSubMenuByParentId(2L);
        System.out.println(list.size());
    }

    @Test
    public void getMenuByRelRoleId() {
        List<SysMenu> list = sysMenuMapper.getMenuByRelRoleId(21L);
        System.out.println(list.size());
    }

}