package com.intellie.data.provider.impl.paper;

import com.intellie.common.utils.PBEUtil;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import com.intellie.data.provider.dao.paper.PaperInfoDao;
import com.intellie.data.provider.service.paper.PaperInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:49
 * @describe:
 */
@Service
public class PaperInfoServiceImpl implements PaperInfoService {
    @Resource
    private PaperInfoDao dao;

    @Value("${data.link}")
    private String link;

    @Override
    public boolean isExistPaper(Paper paper) {
        return (getPaperBaseInfo(paper) == null) ? false : true;
    }

    @Override
    public Paper getPaperBaseInfo(Paper paper) {
        return dao.queryPaperInfo(paper);
    }

    @Override
    public List<PaperComplete> getPaperList(PaperQueryCondition condition) throws ParseException {
        List<PaperComplete> paperCompletes = dao.queryPaperCompleteList(condition);
        for (PaperComplete complete : paperCompletes) {
            paperListHandler(complete, condition.getOwner());
        }
        return paperCompletes;
    }

    public PaperComplete paperListHandler(PaperComplete complete, String owner) {
        String isOpenForever = complete.getIsOpenForever();
        //*****生成考试链接*****/
        String code = complete.getCode();
        //密钥+code =
        if (StringUtil.isNotNull(code)) {
            //试卷创建者+分隔符+试卷编码
            code = owner + BaseConst.linkSplit + code;
            //加密密钥
            String key = PBEUtil.genSecretKey(6);
            //加密后的参数
            String encrypt = null;
            try {
                encrypt = PBEUtil.encrypt(code, key);
                //加密后的参数可能会含有+号 需要转成 其它字符
                encrypt = encrypt.replaceAll("\\+", BaseConst.PLUS_STR);
                //链接 = 固定前缀 + 密钥+ 参数
                complete.setExamLink(link + key + encrypt);
            } catch (Exception e) {
                e.printStackTrace();
                complete.setExamLink(link + "链接无法使用");
            }
        }
        //****判断试卷进行的状态******/
        if (StringUtil.isNull(isOpenForever) || "false".equals(isOpenForever)) {
            try {
                int i = StringUtil.limitTime(complete.getStart(), complete.getEnd());
                switch (i) {
                    case -1:
                        complete.setRunningStatusStr("未开始");
                        break;
                    case 0:
                        complete.setRunningStatusStr("进行中");
                        break;
                    case 1:
                        complete.setRunningStatusStr("已结束");
                        break;
                }
                complete.setRunningStatus(i + "");
            } catch (Exception e) {
                e.printStackTrace();
                complete.setRunningStatusStr("异常");
            }
        } else {
            complete.setRunningStatus("-2");
            complete.setRunningStatusStr("无限制");
        }
        //****判断试卷私密或公开******/
        return complete;
    }
}
