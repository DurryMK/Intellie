package com.intellie.user.provider.mapper;

import com.intellie.common.entity.user.User;
import com.intellie.common.entity.user.UserDetail;
import com.intellie.user.entity.user.UserPaperType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 9:37
 */
@Mapper
public interface UserDao {

    User queryUserBaseInfoById(User user);

    User queryUserBaseInfo(User user);

    UserDetail queryUserDetailInfo(User user);

    void updateAvatar(@Param("imgPath") String imgPath, @Param("user") User user);

    List<UserPaperType> QueryUserPaperTypeList(String id);

    void insertUserPaperType(@Param("id")String id, @Param("newType")String newType);
}
