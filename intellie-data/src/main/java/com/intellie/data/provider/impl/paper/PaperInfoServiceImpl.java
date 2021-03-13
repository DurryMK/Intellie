package com.intellie.data.provider.impl.paper;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.provider.dao.PaperInfoDao;
import com.intellie.data.provider.service.paper.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:49
 * @describe:
 */
@Component
public class PaperInfoServiceImpl implements PaperInfoService {
    @Resource
    private PaperInfoDao dao;

    @Override
    public boolean isExistPaper(Paper paper) {
        Paper paper1 = dao.queryPaperInfo(paper);
        return (paper1 == null) ? false : true;
    }
}
