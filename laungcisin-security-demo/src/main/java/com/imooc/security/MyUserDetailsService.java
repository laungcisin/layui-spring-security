package com.imooc.security;

import com.imooc.security.rbac.role.Role;
import com.imooc.security.rbac.user.User;
import com.imooc.security.rbac.user.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    private UserDao userDao;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
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
        // 1.查找用户信息
        User user = userDao.findUserByUsername(userId);

        if (user == null || StringUtils.isEmpty(user.getUserId())) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //2.判断账户是否被删除
        boolean enabled = true;

        //3.判断账户是否过期
        boolean accountNonExpired = true;

        //3.判断密码是否过期
        boolean credentialsNonExpired = true;

        //4.判断账户是否被锁定
        boolean accountNonLocked = true;

//        password = passwordEncoder.encode(password);

        //获取所有请求的url
        List<Role> roleList = userDao.findRolesByUsername(user.getUsername());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)) {
            for (Role role : roleList) {
                //封装用户信息和角色信息 到 SecurityContextHolder全局缓存中
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));//ROLE_USER-OAuth协议用到
        MySocialUser mySocialUser = new MySocialUser(user.getUsername(), user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                grantedAuthorities);
        mySocialUser.setImgs(user.getImgs());

        return mySocialUser;
    }

}
