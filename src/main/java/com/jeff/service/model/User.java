package com.jeff.service.model;

import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 用户的实体类
 * Created by Jeff on 2018/5/27.
 */
public class User {
    @Id
    private Long id;
    private String name;        // 姓名
    @NotNull
    private String mobile;      // 手机号
    private String email;        // 邮箱
    private String certiNo;      // 身份证号
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertiNo() {
        return certiNo;
    }

    public void setCertiNo(String certiNo) {
        this.certiNo = certiNo;
    }
}
