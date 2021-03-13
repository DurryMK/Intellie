package com.intellie.common.entity.user;

import com.intellie.common.entity.system.BaseEntity;

public class User extends BaseEntity {

    private static final long serialVersionUID = 5054098176905250071L;

    public static final int DEL = -1; //已注销用户
    public static final int EXCEPTION = 1; //账号异常用户
    public static final int NO_DEL = 0; //正常用户

    private String id;//ID
    private String mobile;//手机号
    private String pwd;//密码
    private String username;//用户名
    private int status = NO_DEL;//账号状态

    public User() {}

    public User(String id) {
        this.id = id;
    }

    public User(String username,String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
