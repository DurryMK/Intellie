package com.intellie.data.export.question;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
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
 * @date 2021/3/17 8:56
 * @describe:获取题目信息
 */
@RestController
@RequestMapping("question/info")
public class QuestionController extends BaseController {
    /**
     * 获取用户创建的题目信息列表
     */
    @PostMapping("getQuestionList")
    public Map getQuestionList(HttpServletRequest request, Emap em, QuestionQueryCondition condition) {
        try {
            User loginStatus = commonService.getLoginStatus(request);
            //获取题目分页信息
            Page<Object> helper = PageHelper.startPage(condition.getCurrentPage(), condition.getPageSize());
            List<Question> questionList = questionInfoService.getQuestionList(condition);
            long total = helper.getTotal();
            condition = new QuestionQueryCondition();
            condition.setDel(BaseConst.NO_DEL);
            condition.setOwner(loginStatus.getId());
            condition.setTotal(total);
            //筛选出难度和类型
            Set<String> types = new HashSet<>();
            Set<String> levels = new HashSet<>();
            for (Question question : questionList) {
                types.add(question.getType());
                levels.add(question.getLevel());
            }
            List<String> userQuestionIdList = questionInfoService.getUserQuestionIdList(condition);
            for(Question question : questionList){
                if(userQuestionIdList.contains(question.getId())){
                    question.setIsAdd("1");
                }
            }
            return em.success(new String[]{"condition", "list", "types", "levels"}, condition, questionList, types, levels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("获取信息失败");
    }
    /**
     * 获取用户拥有的题目信息列表
     */
    @PostMapping("getUserQuestionList")
    public Map getUserQuestionList(HttpServletRequest request, Emap em, QuestionQueryCondition condition) {
        try {
            User loginStatus = commonService.getLoginStatus(request);
            //获取题目分页信息
            Page<Object> helper = PageHelper.startPage(condition.getCurrentPage(), condition.getPageSize());
            condition.setOwner(loginStatus.getId());
            condition.setDel(BaseConst.NO_DEL);
            List<Question> questionList = questionInfoService.getUserQuestionList(condition);
            long total = helper.getTotal();
            condition.setTotal(total);
            //筛选出难度和类型
            Set<String> types = new HashSet<>();
            Set<String> levels = new HashSet<>();
            for (Question question : questionList) {
                types.add(question.getType());
                levels.add(question.getLevel());
            }
            return em.success(new String[]{"condition", "list", "types", "levels"}, condition, questionList, types, levels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("获取信息失败");
    }
}
