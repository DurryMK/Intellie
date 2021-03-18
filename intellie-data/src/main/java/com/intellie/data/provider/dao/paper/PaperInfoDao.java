package com.intellie.data.provider.dao.paper;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperComplete;
import com.intellie.data.entity.paper.PaperQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:50
 * @describe:
 */
@Mapper
public interface PaperInfoDao {
    Paper queryPaperInfo(Paper paper);

    List<PaperComplete> queryPaperCompleteList(PaperQueryCondition condition);
}
