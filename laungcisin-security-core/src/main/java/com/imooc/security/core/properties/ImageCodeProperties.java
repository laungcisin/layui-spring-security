package com.imooc.security.core.properties;

/**
 * 图形验证码基本配置
 * @author imooc
 */
public class ImageCodeProperties extends SmsCodeProperties {

    //图形验证码宽度
    private int width = 67;
    //图形验证码高度
    private int height = 23;
    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
