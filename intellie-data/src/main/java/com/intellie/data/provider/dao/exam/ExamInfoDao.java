package com.intellie.data.provider.dao.exam;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:50
 * @describe:
 */
@Mapper
public interface ExamInfoDao {
    //查询一张试卷题目列表的详细信息
    List<Question> queryPaperQuestion(Paper paper);
}
