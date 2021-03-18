package com.intellie.data;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import com.intellie.data.provider.service.question.QuestionInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//        questionInfoService.getUserQuestion("1001", "a")
//        Page<Object> objects = PageHelper.startPage(1, 10);
//        QuestionQueryCondition condition = new QuestionQueryCondition();
//        condition.setDel("0");
//        condition.setCurrentPage(1);
//        condition.setPageSize(30);
//        condition.setIsPage("1");
//        condition.setStartIndex((condition.getCurrentPage()-1)*condition.getPageSize());
//        questionInfoService.getQuestionList(condition);
//        long total = objects.getTotal();
//        return;
    }

}
