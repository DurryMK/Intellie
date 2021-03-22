package com.intellie.data.provider.dao.question;

import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/16 14:42
 * @describe:
 */
@Mapper
public interface QuestionInfoDao {
    List<Question> queryQuestionList(QuestionQueryCondition condition);

    List<Question> queryUserQuestionList(QuestionQueryCondition condition);

    List<String> queryUserQuestionIdList(QuestionQueryCondition condition);

}
