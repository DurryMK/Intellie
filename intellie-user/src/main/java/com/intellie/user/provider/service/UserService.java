package com.intellie.user.provider.service;

import com.intellie.common.entity.user.User;
import com.intellie.common.entity.user.UserDetail;
import com.intellie.user.entity.user.UserPaperType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/11 17:20
 */
@Service
public interface UserService {
    User getUserBaseInfo(User user);

    UserDetail getUserDetailInfo(User user);

    List<UserPaperType> getUserPaperTypeList(String id);

    void modifyAvatar(String imgPath, User user);

    void addUserPaperType(String id, String newType);
}
