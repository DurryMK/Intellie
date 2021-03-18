package com.intellie.data.entity.question;

import com.intellie.common.entity.system.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 17:47
 * @describe:
 */
public class UserQuestion extends BaseEntity {

    private static final long serialVersionUID = -881149077815687823L;

    private String id;
    private String userId;
    private String questionId;
    private String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "UserQuestion{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
