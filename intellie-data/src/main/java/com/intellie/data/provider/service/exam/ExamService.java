package com.intellie.data.provider.service.exam;

import com.intellie.data.entity.paper.Paper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/28 20:45
 * @describe:
 */
@Service
public interface ExamService {
    /***
     *获取一场考试的全部信息
     * paper 试卷信息
     * list 题目列表
     * */
    Map getExamInfo(Paper paper);
}
