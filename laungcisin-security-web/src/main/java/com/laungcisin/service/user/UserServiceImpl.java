package com.laungcisin.service.user;

import com.laungcisin.security.rbac.mybatis.entity.SysUser;
import com.laungcisin.security.rbac.mybatis.mapper.SysUserMapper;
import com.laungcisin.utils.PageData;
import com.laungcisin.utils.PageDataRequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageData getUserList(PageDataRequestParam param) {
        PageData pageData = new PageData();
        try {
            pageData.setCount(sysUserMapper.getAllUserCount());
            List<SysUser> list = sysUserMapper.getUserPageData(param.getPage(), param.getLimit());
            pageData.setData(list);
        } catch (Exception e) {
            pageData.setCode(200);
            pageData.setMsg("查询数据出错");
            logger.error("UserServiceImpl.getUserList()查询数据出错");
        }

        return pageData;
    }
}
