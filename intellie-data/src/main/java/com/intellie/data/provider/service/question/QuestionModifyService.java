package com.intellie.data.provider.service.question;

import org.springframework.stereotype.Service;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 16:19
 * @describe:
 */
@Service
public interface QuestionModifyService {
    void saveUserQuestion(String userId,String questionId);
    void modifyUserQuestion(String userId,String questionId);
}
