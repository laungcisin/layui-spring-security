package com.laungcisin.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SpringSecurity全局配置
 *
 * @author laungcisin
 */
@ConfigurationProperties(prefix = "laungcisin.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

}

