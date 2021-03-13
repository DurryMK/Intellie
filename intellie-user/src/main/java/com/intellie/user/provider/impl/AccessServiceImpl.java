package com.intellie.user.provider.impl;

import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.user.provider.mapper.UserDao;
import com.intellie.user.provider.service.AccessService;
import com.intellie.user.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccessServiceImpl implements AccessService {
    @Resource
    private UserDao dao;
    @Autowired
    private RedisUtil redisUtil;

    private Logger log = LoggerFactory.getLogger(AccessService.class);

    /**
     * 快速登录
     */
    @Override
    public void quickLogin(String mobile, String vcode, Emap em) {
        String orgVcodeKey = CacheTag.VERIFY_TOKEN + mobile;
        //1.验证码是否过期
        if (!redisUtil.hasKey(orgVcodeKey)) {
            em.fail("验证码已失效，请重新获取");
        } else {
            //2.获取缓存的验证码
            String orgVocde = redisUtil.get(orgVcodeKey);
            redisUtil.delete(orgVcodeKey);
            //3.验证码是否正确
            if (!orgVocde.equals(vcode)) {
                em.fail("验证码错误");
            } else {
                //获取用户信息
                User user = new User();
                user.setMobile(mobile);
                user.setStatus(User.NO_DEL);
                user = dao.queryUserBaseInfo(user);
                if (user == null) {//3.用户是否存在
                    em.fail("用户不存在");
                } else {
                    //登录成功
                    em.success(user);
                }
            }
        }
    }

    /**
     * 自动登录
     */
    @Override
    public void autoLogin(String token, Emap em) {
        //1.从redis读取登录token
        if (!redisUtil.hasKey(token)) {
            em.fail("登录已过期");
            return;
        }
        //2.读取登录信息
        String userId = redisUtil.get(token);
        //3.获取用户信息
        User user = new User(userId);
        user = dao.queryUserBaseInfoById(user);
        if (user == null) {
            em.fail("用户不存在");
            return;
        }
        em.success(user.getId());
    }

    @Override
    public void normalLogin(String token, String pwd, Emap em) {
        User user = new User();
        user.setUsername(token);
        if (dao.queryUserBaseInfo(user) == null) {
            em.fail("用户名不存在");
            return;
        }
        user.setPwd(pwd);
        User result = dao.queryUserBaseInfo(user);
        if (result == null) {
            em.fail("密码错误");
            return;
        }
        em.success(result);

    }


}
