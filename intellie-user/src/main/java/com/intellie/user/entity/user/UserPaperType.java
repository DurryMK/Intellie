package com.intellie.user.entity.user;

import com.intellie.common.entity.system.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/12 18:36
 * @describe:用户自定义的试卷类型实体类
 */
public class UserPaperType extends BaseEntity {
    private static final long serialVersionUID = -9150469816924042190L;
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserPaperType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
