package com.intellie.data.provider.dao.paper;

import com.intellie.data.entity.paper.Paper;
import com.intellie.data.entity.paper.PaperAttribute;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:50
 * @describe:
 */
@Mapper
public interface PaperModifyDao {
    void insertPaper(Paper paper);

    void updatePaper(Paper paper);

    void insertPaperAttribute(PaperAttribute paperAttribute);

    void updatePaperAttribute(PaperAttribute paperAttribute);
}