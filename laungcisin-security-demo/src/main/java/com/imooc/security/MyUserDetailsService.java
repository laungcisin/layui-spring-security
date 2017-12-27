package com.imooc.security;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author imooc
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //密码加密器-用于加密,密码匹配
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("设计登录用户Id:" + userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        //TODO:修改成从数据库中获取用户信息
        // 1.查找用户信息
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user where username = ? limit 1", userId);
        String password = CollectionUtils.size(list) > 0 ? (String) list.get(0).get("password") : "";

        //2.判断账户是否被删除
        boolean enabled = true;

        //3.判断账户是否过期
        boolean accountNonExpired = true;

        //3.判断密码是否过期
        boolean credentialsNonExpired = true;

        //4.判断账户是否被锁定
        boolean accountNonLocked = true;

//        password = passwordEncoder.encode(password);

        return new SocialUser(userId, password,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));//ROLE_USER-OAuth协议用到
    }

}
