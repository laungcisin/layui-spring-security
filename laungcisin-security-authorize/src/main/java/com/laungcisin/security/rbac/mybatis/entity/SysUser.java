package com.laungcisin.security.rbac.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private Long userId;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private Short status;

    private Long createUserId;

    private Date createTime;

    private Date accountExpiredTime;

    private Date passwordExpiredTime;

    private Integer isLocked;

    private Integer isDeleted;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAccountExpiredTime() {
        return accountExpiredTime;
    }

    public void setAccountExpiredTime(Date accountExpiredTime) {
        this.accountExpiredTime = accountExpiredTime;
    }

    public Date getPasswordExpiredTime() {
        return passwordExpiredTime;
    }

    public void setPasswordExpiredTime(Date passwordExpiredTime) {
        this.passwordExpiredTime = passwordExpiredTime;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}