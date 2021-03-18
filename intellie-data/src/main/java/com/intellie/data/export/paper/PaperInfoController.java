package com.intellie.data.export.paper;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.export.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            List<PaperComplete> paperList = paperInfoService.getPaperList(condition);
            condition.setTotal(paperList.size());

            condition.setIsPage("1");
            condition.setStartIndex((condition.getCurrentPage() - 1) * condition.getPageSize());
            List<PaperComplete> paperList2 = paperInfoService.getPaperList(condition);
            return em.success(new String[]{"condition", "list"}, condition, paperList2);
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
        try {
            int score = Integer.parseInt(scoreStr);
            int level = Integer.parseInt(levelStr);
            if (score % 5 != 0 || score > 500 || score < 5 || level < 1 || level > 5)
                return em.fail("创建失败,请重试");
        } catch (Exception e) {
            return em.fail("创建失败,请重试");
        }
        User loginStatus = commonService.getLoginStatus(request);
        Paper paper = new Paper();
        paper.setOwner(loginStatus.getId());
        paper.setCode(code);
        try {
            //判断该用户是否有该试卷
            if (!paperInfoService.isExistPaper(paper))
                return em.fail("创建失败,请重试");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail();
    }

    public static void main(String[] args) {
        int score = 120;
        int level = 5;
        int[] levels = new int[]{2, 3, 4};

        Map<Integer, Integer> oc = new LinkedHashMap();
        int count = 0;
        int i = 100 / levels.length;
        int i1 = 100 % levels.length;
        for (int item : levels) {
            if (count == levels.length - 1)
                oc.put(item, i + i1);
            else
                oc.put(item, i);
            count++;
        }

        int[] od = new int[levels.length];
        for (int j = 0; j < 100; j++) {
            
        }
    }
}
