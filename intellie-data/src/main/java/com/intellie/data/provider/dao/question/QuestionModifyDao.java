package com.intellie.data.provider.dao.question;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 16:14
 * @describe:
 */
@Mapper
public interface QuestionModifyDao {
    void insertUserQuestion(@Param("userId") String userId, @Param("questionId") String questionId);
}
