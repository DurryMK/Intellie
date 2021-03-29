package com.intellie.user.provider.impl;

import com.intellie.common.entity.system.SIGN;
import com.intellie.common.entity.user.User;
import com.intellie.common.entity.user.UserDetail;
import com.intellie.common.utils.StringUtil;
import com.intellie.user.entity.user.UserPaperType;
import com.intellie.user.provider.mapper.UserDao;
import com.intellie.user.provider.service.UserService;
import com.intellie.user.utils.AgeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 9:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 图片服务器地址
     */
    @Value("${img.imgAddress}")
    public String imgAddress;

    /**
     * 用户头像图片库
     */
    @Value("${img.avatar}")
    public String avatar;

    @Override
    public User getUserBaseInfo(User user) {
        return userDao.queryUserBaseInfo(user);
    }

    @Override
    public UserDetail getUserDetailInfo(User user) {
        UserDetail userDetail = userDao.queryUserDetailInfo(user);
        //隐藏手机号中间4位
        userDetail.setMobile(userDetail.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        //计算当前年龄
        userDetail.setBirthday(AgeUtil.get(userDetail.getBirthday()) + "");
        userDetail.setImg(imgAddress + SIGN.URLSplit + avatar + userDetail.getImg());
        //隐藏真实姓名
        String realName = userDetail.getRealName();
        if (StringUtil.isNotNull(realName)) {
            if (realName.length() == 2) {
                realName = "*" + realName.substring(1);
            } else if (realName.length() == 3) {
                realName = realName.substring(0, 1) + "*" + realName.substring(2);
            } else {
                realName = realName.substring(0, 1) + "***" + realName.substring(realName.length() - 1);
            }
            userDetail.setRealName(realName);
        }
        //隐藏身份证号
        String idCard = userDetail.getIdCard();
        if (StringUtil.isNotNull(idCard)) {
            idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1****$2");
            userDetail.setIdCard(idCard);
        }
        return userDetail;
    }

    @Override
    public List<UserPaperType> getUserPaperTypeList(String id) {
        return userDao.QueryUserPaperTypeList(id);
    }

    @Override
    public void modifyAvatar(String imgPath, User user) {
        userDao.updateAvatar(imgPath, user);
    }

    @Override
    public void addUserPaperType(String id, String newType) {
        userDao.insertUserPaperType(id, newType);
    }

}
