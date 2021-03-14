package com.intellie.data.provider.impl.paper;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.provider.dao.PaperInfoDao;
import com.intellie.data.provider.service.paper.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<PaperComplete> getPaperList(Paper paper) {
        return dao.queryPaperCompleteList(paper);
    }
}
