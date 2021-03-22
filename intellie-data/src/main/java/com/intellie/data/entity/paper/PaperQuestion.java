package com.intellie.data.entity.paper;

import com.intellie.common.entity.system.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/21 9:10
 * @describe:
 */
public class PaperQuestion extends BaseEntity {
    private static final long serialVersionUID = -6827191012424558955L;

    private String id;
    private String paperId;
    private String questionId;
    private String score;
    private String sort;

    @Override
    public String toString() {
        return "PaperQuestion{" +
                "id='" + id + '\'' +
                ", paperId='" + paperId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", score='" + score + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
