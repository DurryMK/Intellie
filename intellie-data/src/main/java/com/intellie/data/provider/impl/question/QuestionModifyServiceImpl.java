package com.intellie.data.provider.impl.question;

import com.intellie.data.provider.dao.question.QuestionModifyDao;
import com.intellie.data.provider.service.question.QuestionModifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 16:20
 * @describe:
 */
@Service
public class QuestionModifyServiceImpl implements QuestionModifyService {

    @Resource
    private QuestionModifyDao dao;

    @Override
    public void saveUserQuestion(String userId, String questionId) {
        dao.insertUserQuestion(userId, questionId);
    }

    @Override
    public void modifyUserQuestion(String userId, String questionId) {

    }
}
