package com.intellie.data.provider.impl.exam;

import com.intellie.common.entity.system.Emap;
import com.intellie.data.entity.base.BaseConst;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import com.intellie.data.provider.dao.exam.ExamInfoDao;
import com.intellie.data.provider.dao.paper.PaperInfoDao;
import com.intellie.data.provider.impl.paper.PaperInfoServiceImpl;
import com.intellie.data.provider.service.exam.ExamService;
import com.intellie.data.provider.service.paper.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/28 20:45
 * @describe:
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamInfoDao examInfoDao;
    @Resource
    private PaperInfoDao paperInfoDao;
    @Autowired
    private PaperInfoServiceImpl paperInfoServiceImpl;

    @Override
    public Map getExamInfo(Paper paper) {
        Emap em = new Emap();
        PaperQueryCondition condition = new PaperQueryCondition();
        condition.setCode(paper.getCode());
        condition.setOwner(paper.getOwner());
        condition.setDel(BaseConst.NO_DEL);
        condition.setPersonal(BaseConst.NO_PERSONAL);
        PaperComplete complete = paperInfoDao.queryPaperCompleteList(condition).get(0);
        complete = paperInfoServiceImpl.paperListHandler(complete, paper.getOwner());
        if (complete == null)
            return em.fail("试卷不存在");
        paper.setId(complete.getId());
        List<Question> questions = examInfoDao.queryPaperQuestion(paper);
        return em.success(new String[]{"paper", "list"}, complete, questions);
    }
}
