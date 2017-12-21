package com.imooc.security.core.properties;

public class OAuth2Properties {
    private OAuth2ClientProperties[] clients;

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
