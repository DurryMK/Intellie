package com.intellie.data.provider.dao;

import com.intellie.data.entity.paper.Paper;
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
}
