package com.intellie.data.provider.service.question;

import com.github.pagehelper.PageInfo;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 8:59
 * @describe:
 */
@Service
public interface QuestionInfoService {
    /**
     * 查询指定条件的题目列表
     */
    List<Question> getQuestionList(QuestionQueryCondition condition);

    /**
     * 查询用户拥有的题目信息列表
     */
    List<Question> getUserQuestionList(QuestionQueryCondition condition);

    /**
     * 查询用户拥有的题目ID列表
     */
    List<String> getUserQuestionIdList(QuestionQueryCondition condition);
}
