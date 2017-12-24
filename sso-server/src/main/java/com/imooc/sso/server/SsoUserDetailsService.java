package com.imooc.sso.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Component
public class SsoUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        return buildUser(username);
    }


    private UserDetails buildUser(String userId) {
        //TODO:修改成从数据库中获取用户信息
        // 1.查找用户信息
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user where username = ? limit 1", userId);
        String password = !CollectionUtils.isEmpty(list) ? (String) list.get(0).get("password") : "";

        //2.判断账户是否被删除
        boolean enabled = true;

        //3.判断账户是否过期
        boolean accountNonExpired = true;

        //3.判断密码是否过期
        boolean credentialsNonExpired = true;

        //4.判断账户是否被锁定
        boolean accountNonLocked = true;

        return new User(userId, password,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));//ROLE_USER-OAuth协议用到
    }
}
