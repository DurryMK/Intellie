package com.intellie.data.provider.impl.paper;

import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import com.intellie.data.export.base.BaseController;
import com.intellie.data.provider.dao.paper.PaperInfoDao;
import com.intellie.data.provider.service.paper.PaperInfoService;
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
        return paperListHandler(paperCompletes);
    }

    @Override
    public PaperComplete getPaperExamInfo(String code) {
        return null;
    }

    @Override
    public List<Question> getPaperQuestionList(Paper paper) {
        return dao.queryPaperQuestionList(paper);
    }

    private List<PaperComplete> paperListHandler(List<PaperComplete> list) {
        for (PaperComplete complete : list) {
            String isOpenForever = complete.getIsOpenForever();
            //不是永久开放则判断进行状态
            if (StringUtil.isNull(isOpenForever) || "false".equals(isOpenForever)) {
                try {
                    complete.setIsRunning(StringUtil.limitTime(complete.getStart(), complete.getEnd()) + "");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //考试时长
            if (StringUtil.isNotNull(complete.getDelivery()) && "true".equals(complete.getDelivery())) {
                String duration = complete.getDuration();
            }
        }
        return list;
    }
}
