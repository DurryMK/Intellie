package com.intellie.data.export.paper;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.common.entity.user.UserDetail;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperAttribute;
import com.intellie.data.export.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 12:20
 * @describe:修改试卷信息控制器
 */
@RestController
@RequestMapping("paper/modify")
public class PaperModifyController extends BaseController {


//    title: '',
//    type: '0',
//    remark: '',
//    level: 2,
//    code: null,

    /**
     * 创建一张新的试卷
     */
    @PostMapping("createNewPaper")
    public Map createNewPaper(HttpServletRequest request, Emap em) {

        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String level = request.getParameter("level");
        String remark = request.getParameter("remark");
        String code = request.getParameter("code");

        if (StringUtil.isNull(title) || StringUtil.isNull(type) || StringUtil.isNull(level) || StringUtil.isNull(code)) {
            return em.fail("试卷属性不完整");
        }

        try {
            User loginStatus = commonService.getLoginStatus(request);
            Paper paper = new Paper();
            paper.setCode(code);
            boolean existPaper = paperInfoService.isExistPaper(paper);
            paper.setTitle(title);
            paper.setOwner(loginStatus.getId());
            paper.setType(type);
            paper.setLevel(level);
            paper.setRemark(remark);
            if (!existPaper) {
                //创建新的
                paperModifyService.createNewPaper(paper);
            } else {
                //修改原有的
                paperModifyService.modifyPaper(paper);
            }
            return em.success("成功保存试卷");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("试卷创建失败");
    }

//    /**
//     * 修改试卷的内容
//     */
//    @PostMapping("modifyPaper")
//    public Map modifyPaper(HttpServletRequest request, Emap em) {
//
//        String title = request.getParameter("title");
//        String type = request.getParameter("type");
//        String level = request.getParameter("level");
//        String remark = request.getParameter("remark");
//        String code = request.getParameter("code");
//
//        if (StringUtil.isNull(title) || StringUtil.isNull(type) || StringUtil.isNull(level) || StringUtil.isNull(code)) {
//            return em.fail("试卷属性不完整");
//        }
//
//        try {
//            User loginStatus = commonService.getLoginStatus(request);
//            Paper paper = new Paper();
//            paper.setCode(code);
//            paper.setOwner(loginStatus.getId());
//
//            if (!paperInfoService.isExistPaper(paper))
//                return em.fail("试卷不存在");
//
//            paper.setTitle(title);
//            paper.setType(type);
//            paper.setLevel(level);
//            paper.setRemark(remark);
//
//            paperModifyService.modifyPaper(paper);
//            return em.success("成功保存试卷");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return em.fail("试卷属性修改失败");
//    }

    @PostMapping("modifyPaperAttribute")
    public Map modifyPaperAttribute(HttpServletRequest request, Emap em, PaperAttribute attribute) {
        try {
            String code = request.getParameter("code");
            Paper paper = new Paper();
            paper.setCode(code);
            paper = paperInfoService.getPaperBaseInfo(paper);
            if (paper == null)
                return em.fail("试卷不存在");
            paperModifyService.modifyPaperAttribute(paper, attribute);
            return em.success("设置成功");
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("设置失败");
        }
    }
}
