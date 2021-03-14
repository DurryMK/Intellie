package com.intellie.data.export.paper;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
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
 * @date 2021/3/14 11:32
 * @describe:获取试卷信息的控制器
 */
@RestController
@RequestMapping("paper/info")
public class PaperInfoController extends BaseController {
    /**
     * 创建一张新的试卷
     */
    @PostMapping("getPaperList")
    public Map getPaperList(HttpServletRequest request, Emap em) {
        try {
            User loginStatus = commonService.getLoginStatus(request);
            Paper paper = new Paper();
            List<PaperComplete> paperList = paperInfoService.getPaperList(paper);
            return em.success("成功保存试卷");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("试卷创建失败");
    }
}
