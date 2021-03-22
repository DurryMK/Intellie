package com.intellie.data.export.paper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import com.intellie.data.export.base.BaseController;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/14 11:32
 * @describe:获取试卷信息的控制器
 */
@RestController
@RequestMapping("paper/info")
public class PaperInfoController extends BaseController {
    /**
     * 获取试卷完整信息列表
     */
    @PostMapping("getPaperList")
    public Map getPaperList(HttpServletRequest request, Emap em, PaperQueryCondition condition) {
        try {
            User loginStatus = commonService.getLoginStatus(request);
            condition.setOwner(loginStatus.getId());
            condition.setDel(BaseConst.NO_DEL);
            //获取题目分页信息
            Page<Object> helper = PageHelper.startPage(condition.getCurrentPage(), condition.getPageSize());
            List<PaperComplete> paperList = paperInfoService.getPaperList(condition);
            condition.setTotal(helper.getTotal());
            return em.success(new String[]{"condition", "list"}, condition, paperList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("获取信息失败");
    }

    /**
     * 随机生成一张试卷的题目列表
     */
    @PostMapping("createRandomPaper")
    public Map createRandomPaper(HttpServletRequest request, Emap em) {
        String scoreStr = request.getParameter("score");//试卷分值
        String code = request.getParameter("code");//试卷编码
        String checkStr = request.getParameter("check");//题目来源 1我的 2系统
        String levelStr = request.getParameter("level");
        String questionNumStr = request.getParameter("questionNum");
        try {
            int score = Integer.parseInt(scoreStr);
            int level = Integer.parseInt(levelStr);
            int questionNum = Integer.parseInt(questionNumStr);
            if (score % 5 != 0 || score > 500 || score < 5 || level < 1 || level > 5 || StringUtil.isNull(checkStr))
                return em.fail("创建失败,请重试");
            QuestionQueryCondition condition;
            List<Question> questionList;
            User loginStatus = commonService.getLoginStatus(request);
            //获取题目列表
            if ("1".equals(checkStr)) {
                //只获取我的题库列表
                condition = new QuestionQueryCondition();
                condition.setOwner(loginStatus.getId());
                condition.setDel(BaseConst.NO_DEL);
                questionList = questionInfoService.getUserQuestionList(condition);
            } else if ("2".equals(checkStr)) {
                //只获取系统题库列表
                condition = new QuestionQueryCondition();
                condition.setOwner("0");
                condition.setDel(BaseConst.NO_DEL);
                questionList = questionInfoService.getQuestionList(condition);
            } else if ("0&1".equals(checkStr) || "1&0".equals(checkStr)) {
                //两种都获取
                condition = new QuestionQueryCondition();
                condition.setOwner(loginStatus.getId());
                condition.setDel(BaseConst.NO_DEL);
                questionList = questionInfoService.getUserQuestionList(condition);
                condition = new QuestionQueryCondition();
                condition.setOwner("0");
                condition.setDel(BaseConst.NO_DEL);
                List<Question> questionList2 = questionInfoService.getQuestionList(condition);
                questionList.addAll(questionList2);
            } else {
                return em.fail("创建失败,请重试");
            }
            Paper paper = new Paper();
            paper.setOwner(loginStatus.getId());
            paper.setCode(code);
//            //判断该用户是否有该试卷
//            if (!paperInfoService.isExistPaper(paper))
//                return em.fail("创建失败,请重试");
            //TODO:随机试卷生成算法
            Random rd = new Random();
            List<Question> randomList = new ArrayList<>();
            while (questionNum > 0) {
                Question question = questionList.get(rd.nextInt(questionList.size()));
                if (!randomList.contains(question)) {
                    question.setScore(10);
                    question.setSort(questionNum);
                    if (question.getContent().length() > 20)
                        question.setPreView(question.getContent().substring(0, 21)+"...");
                    else
                        question.setPreView(question.getContent());
                    randomList.add(question);
                    questionNum--;
                }
            }
            return em.success(randomList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("创建失败,请重试");
    }
}
