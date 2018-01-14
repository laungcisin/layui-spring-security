package com.laungcisin.security.core.support;

/**
 * @author laungcisin
 */
public class SimpleResponse {

    private Object code;
    private Object content;

    public SimpleResponse(Object code, Object content) {
        this.code = code;
        this.content = content;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
