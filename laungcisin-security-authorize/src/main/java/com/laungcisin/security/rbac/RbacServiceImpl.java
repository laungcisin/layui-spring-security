package com.laungcisin.security.rbac;

import com.laungcisin.security.rbac.mybatis.entity.SysMenu;
import com.laungcisin.security.rbac.mybatis.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;

        //web|app权限判断
        if (principal instanceof UserDetails || authentication instanceof OAuth2Authentication) {
            String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : (String)authentication.getPrincipal();

            //读取用户所拥有权限的所有URL
            List<SysMenu> menuList = sysMenuMapper.getAllMenuByUsername(username);
            if (!CollectionUtils.isEmpty(menuList)) {
                //TODO:根据自己的数据库改成相应的逻辑
                Set<String> urls = new HashSet<>();
                for (SysMenu menu : menuList) {
                    if (!StringUtils.isEmpty(menu.getPermissions())) {
                        String[] permissionArray = menu.getPermissions().split(",");
                        if (permissionArray == null || permissionArray.length < 1) {
                            continue;
                        }

                        urls.addAll(CollectionUtils.arrayToList(permissionArray));
                    }
                }

                urls.add("/");

                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            } else {
                if (antPathMatcher.match("/", request.getRequestURI())) {
                    hasPermission = true;
                }
            }
        }

        return hasPermission;
    }
}
