package com.intellie.data.provider.impl.paper;

import com.intellie.common.utils.CommonUtils;
import com.intellie.common.utils.PBEUtil;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperAttribute;
import com.intellie.data.entity.paper.PaperQuestion;
import com.intellie.data.provider.dao.paper.PaperModifyDao;
import com.intellie.data.provider.service.paper.PaperModifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 12:02
 * @describe:
 */
@Service
public class PaperModifyServiceImpl implements PaperModifyService {
    @Resource
    private PaperModifyDao dao;

    //试卷的默认封面图片地址
    private final String IMG_URL = "./img/default.png";

    @Override
    public void createNewPaper(Paper paper) {
        //创建一张新的试卷
        paper.setId(PBEUtil.genSecretKey(12));
        if (StringUtil.isNull(paper.getImgUrl()))
            paper.setImgUrl(IMG_URL);
        String time = CommonUtils.getDateString(System.currentTimeMillis());
        paper.setTime(time);
        paper.setModifyTime(time);
        paper.setDel(Paper.NO_DEL);
        dao.insertPaper(paper);
        //创建对应的试卷属性
        PaperAttribute paperAttribute = new PaperAttribute();
        //随机序列号
        paperAttribute.setId(PBEUtil.genSecretKey(12));
        //对应的试卷Id
        paperAttribute.setPaperId(paper.getId());
        dao.insertPaperAttribute(paperAttribute);
    }

    @Override
    public void modifyPaperAttribute(Paper paper, PaperAttribute attribute) {
        attribute.setPaperId(paper.getId());
        attribute.setId(PBEUtil.genSecretKey(12));
        attribute.setModifyTime(CommonUtils.getDateString(System.currentTimeMillis()));
        dao.updatePaperAttribute(attribute);
    }

    @Override
    public void addPaperQuestion(List<PaperQuestion> list) {
        dao.insertPaperQuestion(list);
    }


    @Override
    public void modifyPaper(Paper paper) {
        paper.setModifyTime(CommonUtils.getDateString(System.currentTimeMillis()));
        dao.updatePaper(paper);
    }
}
