package com.intellie.data.export.question;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.question.Question;
import com.intellie.data.entity.question.QuestionQueryCondition;
import com.intellie.data.export.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/17 16:23
 * @describe:
 */
@RestController
@RequestMapping("question/modify")
public class QuestionModifyController extends BaseController {
    /**
     * 获取题目信息列表
     */
    @PostMapping("saveUserQuestion")
    public Map saveUserQuestion(HttpServletRequest request, Emap em) {
        try {
            User loginStatus = commonService.getLoginStatus(request);
            String userId = loginStatus.getId();
            String questionId = request.getParameter("questionId");
            QuestionQueryCondition condition = new QuestionQueryCondition();
            condition.setDel(BaseConst.NO_DEL);
            condition.setOwner(userId);
            condition.setQuestionId(questionId);
            List<Question> userQuestion = questionInfoService.getUserQuestionList(condition);
            if (userQuestion.size() > 0)
                return em.fail("保存信息失败");
            questionModifyService.saveUserQuestion(userId, questionId);
            return em.success("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("保存信息失败");
    }
}
