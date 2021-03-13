package com.intellie.common.entity.user;

import com.intellie.common.entity.system.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/11 17:16
 */
public class UserDetail extends BaseEntity {
    private static final long serialVersionUID = 3580362928068902516L;

    private String mobile;//手机号
    private String username;//用户名
    private String birthday;//年龄
    private String email;//邮箱
    private String qq;//QQ
    private String rName;//是否实名
    private String school;//就读学校
    private String img;//头像链接
    private String RegTime;//注册时间
    private String major;//专业

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String regTime) {
        RegTime = regTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", rName='" + rName + '\'' +
                ", school='" + school + '\'' +
                ", imgUrl='" + img + '\'' +
                ", RegTime='" + RegTime + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
