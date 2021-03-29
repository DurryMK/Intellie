package com.intellie.data.provider.service.paper;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import com.intellie.data.entity.question.Question;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:38
 * @describe:获取试卷信息
 */
@Service
public interface PaperInfoService {
    /**
     *判断指定试卷是否存在
     * */
    boolean isExistPaper(Paper paper);

    /**
     *获取指定试卷的基本信息
     * */
    Paper getPaperBaseInfo(Paper paper);

    /**获取试卷列表信息*/
    List<PaperComplete> getPaperList(PaperQueryCondition condition) throws ParseException;
}
