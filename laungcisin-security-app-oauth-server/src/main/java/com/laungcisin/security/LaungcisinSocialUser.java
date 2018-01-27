package com.laungcisin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;

public class LaungcisinSocialUser extends SocialUser {
    private Long id;

    public LaungcisinSocialUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public LaungcisinSocialUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
