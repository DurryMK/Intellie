package com.intellie.data.provider.impl.common;

import com.alibaba.fastjson.JSON;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.PBEUtil;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.provider.service.common.CommonService;
import com.intellie.data.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:08
 * @describe:
 */
public class CommonServiceImpl implements CommonService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getLoginIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie loginCookie = null;
        for (Cookie cookie : cookies) {
            if ("INE".equals(cookie.getName()))
                loginCookie = cookie;
        }
        if (loginCookie == null)
            return null;
        return loginCookie.getValue();
    }

    @Override
    public String decodeLoginToken(HttpServletRequest request) {
        String loginIdFromCookie = getLoginIdFromCookie(request);
        if (loginIdFromCookie == null)
            return null;
        try {
            String secretKey = loginIdFromCookie.substring(0, 6);
            String token = loginIdFromCookie.substring(6);
            token = URLDecoder.decode(token, "UTF-8");
            String decrypt = PBEUtil.decrypt(token, secretKey);
            return decrypt;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getLoginStatus(HttpServletRequest request) {
        String token = decodeLoginToken(request);
        if (StringUtil.isNull(token))
            return null;
        try {
            String userString = redisUtil.get(token);
            User user = JSON.parseObject(userString, User.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
