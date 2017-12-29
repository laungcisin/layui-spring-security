package com.imooc.security.rbac;

import com.imooc.security.rbac.permissioin.Permission;
import com.imooc.security.rbac.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private UserDao uerDao;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();

            //读取用户所拥有权限的所有URL
            List<Permission> permissionList = uerDao.findPermissionsByUsername(username);
            if (!CollectionUtils.isEmpty(permissionList)) {
                //TODO:根据自己的数据库改成相应的逻辑
                Set<String> urls = new HashSet<>();
                for (Permission permission : permissionList) {
                    urls.add(permission.getUrl());
                }

                urls.add("/");

                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
        }

        return hasPermission;
    }
}