package com.intellie.data.export.examination;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import com.intellie.data.export.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/22 8:37
 * @describe:考试控制器
 */
@RestController
@RequestMapping("exam")
public class ExamController extends BaseController {

    @PostMapping("initExam")
    public Map initExam(HttpServletRequest request, Emap em) {
        String code = request.getParameter("code");
        if (StringUtil.isNull(code)) {
            return em.fail("获取试卷错误");
        }
        //:TODO 对试卷编码进行加解密
        PaperComplete complete = null;
        try {
            Paper paper = new Paper();
            paper.setCode(code);
            //查询试卷基本信息
            paper = paperInfoService.getPaperBaseInfo(paper);
            PaperQueryCondition condition = new PaperQueryCondition();
            condition.setDel(BaseConst.NO_DEL);
            condition.setKey(code);
            condition.setOwner(paper.getOwner());
            condition.setPersonal(BaseConst.NO_PERSONAL);
            //查询完整信息
            complete = paperInfoService.getPaperList(condition).get(0);
            //获取题目列表
            QuestionQueryCondition questionQueryCondition = new QuestionQueryCondition();
            questionQueryCondition.setPaperId(paper.getId());
            questionQueryCondition.setDel(BaseConst.NO_DEL);
            List<Question> questionList = questionInfoService.getQuestionList(questionQueryCondition);
            long totalScore = 0;
            //计算总分
            for (Question question : questionList)
                totalScore += question.getScore();
            complete.setTotalScore(totalScore);
            return em.success(new String[]{"paper", "list"}, complete, questionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("拉取试卷信息错误");
    }
}
