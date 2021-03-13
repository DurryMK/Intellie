package com.intellie.demo.demo210303;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 9:37
 */
@Mapper
public interface UserDao {
    String testQuery(@Param("str") String str);
}
