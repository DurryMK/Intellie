package com.intellie.data;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import com.intellie.data.provider.service.question.QuestionInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class DataApplicationTests {

    @Test
    void contextLoads() {
        test1();
    }

    @Autowired
    private QuestionInfoService questionInfoService;

    @Test
    private void test1() {
        QuestionQueryCondition condition = new QuestionQueryCondition();
        condition.setOwner("0");
        condition.setDel(BaseConst.NO_DEL);
        List<Question> questionList2 = questionInfoService.getQuestionList(condition);
        return;
    }

}
