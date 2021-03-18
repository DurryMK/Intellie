package com.intellie.data.provider.impl.question;

import com.github.pagehelper.PageInfo;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import com.intellie.data.provider.dao.question.QuestionInfoDao;
import com.intellie.data.provider.service.question.QuestionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 9:00
 * @describe:
 */
@Service
public class QuestionInfoServiceImpl implements QuestionInfoService {

    @Resource
    private QuestionInfoDao dao;

    @Override
    public List<Question> getQuestionList(QuestionQueryCondition condition) {
        return dao.queryQuestionList(condition);
    }

    @Override
    public  List<Question> getUserQuestionList(QuestionQueryCondition condition) {
        return dao.queryUserQuestionList(condition);
    }

    @Override
    public List<String> getUserQuestionIdList(QuestionQueryCondition condition) {
        return dao.queryUserQuestionIdList(condition);
    }

}
