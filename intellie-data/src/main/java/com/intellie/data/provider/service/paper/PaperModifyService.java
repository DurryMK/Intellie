package com.intellie.data.provider.service.paper;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperAttribute;
import com.intellie.data.entity.paper.PaperQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:31
 * @describe:修改试卷属性
 */
@Service
public interface PaperModifyService {
    void createNewPaper(Paper paper);

    void modifyPaperAttribute(Paper paper, PaperAttribute attribute);

    void addPaperQuestion(List<PaperQuestion> list);

    void modifyPaper(Paper paper);
}
