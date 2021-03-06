package com.intellie.data.export.examination;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.utils.PBEUtil;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.export.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
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
            return em.fail("获取试卷信息错误");
        }
        //试卷创建者
        String owner;
        //试卷编码
        String realCode;
        try {
            //对编码进行解密 前六位密钥
            String key = code.substring(0, 6);
            String orgCode = code.substring(6);
            orgCode = URLDecoder.decode(orgCode, "UTF-8");
            orgCode = orgCode.replaceAll(BaseConst.PLUS_STR, "+");
            //解密后参数
            code = PBEUtil.decrypt(orgCode, key);
            String[] split = code.split(BaseConst.linkSplit);
            owner = split[0];
            realCode = split[1];
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("获取试卷信息错误");
        }
        try {
            Paper paper = new Paper();
            paper.setCode(realCode);
            paper.setOwner(owner);
            return examService.getExamInfo(paper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("拉取试卷信息错误");
    }

}
