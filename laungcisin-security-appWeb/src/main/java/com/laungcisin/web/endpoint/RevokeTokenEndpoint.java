package com.laungcisin.web.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public String revokeToken(String access_token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
        if (accessToken == null) {
            return "注销失败";
        }
        if (accessToken.getRefreshToken() != null) {
            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }

        tokenStore.removeAccessToken(accessToken);
        return "注销成功";
    }
}