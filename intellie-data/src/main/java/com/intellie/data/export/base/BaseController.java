package com.intellie.data.export.base;

import com.intellie.data.provider.service.common.CommonService;
import com.intellie.data.provider.service.exam.ExamService;
import com.intellie.data.provider.service.paper.PaperInfoService;
import com.intellie.data.provider.service.paper.PaperModifyService;
import com.intellie.data.provider.service.question.QuestionInfoService;
import com.intellie.data.provider.service.question.QuestionModifyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 12:21
 * @describe:
 */
public abstract class BaseController {
    @Autowired
    protected PaperInfoService paperInfoService;

    @Autowired
    protected PaperModifyService paperModifyService;

    @Autowired
    protected CommonService commonService;

    @Autowired
    protected QuestionInfoService questionInfoService;

    @Autowired
    protected QuestionModifyService questionModifyService;
    @Autowired
    protected ExamService examService;
}
