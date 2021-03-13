package com.intellie.user.export.base;

import com.alibaba.fastjson.JSON;
import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.StringUtil;
import com.intellie.user.provider.service.AccessService;
import com.intellie.user.provider.service.UserService;
import com.intellie.user.utils.PBEUtil;
import com.intellie.user.utils.RSAUtil;
import com.intellie.user.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/16 7:37
 */
public abstract class AbstractController {
    @Autowired
    protected RedisUtil redisUtil;
    @Autowired
    protected UserService userService;
    @Autowired
    protected AccessService accessService;

    /**
     * 登录记录保存时长
     */
    @Value("${user.loginCache}")
    public int loginCacheTime;

    /**
     * API访问列表过期时间
     */
    @Value("${api.list.overtime}")
    public int overtime;

    /**
     * 图片服务器地址
     */
    @Value("${img.imgAddress}")
    public String imgAddress;

    /**
     * 用户头像图片库名称
     */
    @Value("${img.avatar}")
    public String avatarBucket;

    /**
     * 用户头像图片大小限制
     */
    @Value("${img.avatarLimit}")
    public int avatarLimit;

    /**
     * 用户登录状态维持时间
     */
    @Value("${user.heartTime}")
    public int heartTime;

    protected void apiTokenHandle(HttpServletRequest request) throws NoSuchAlgorithmException {
        //先删除原有的token列表
        String apiListName = (String) request.getSession().getAttribute(CacheTag.API_LIST_NAME);
        if (StringUtils.isNotEmpty(apiListName) && redisUtil.hasKey(apiListName)) {
            redisUtil.delete(apiListName);
        }
        //生成api访问的token列表名称
        apiListName = UUID.randomUUID().toString().replaceAll("-", "") + System.currentTimeMillis();
        //token列表
        List<String> auList = new ArrayList<>();
        auList.add("init");
        request.getSession().setAttribute(CacheTag.API_LIST_NAME, apiListName);
        redisUtil.lRightPushAll(apiListName, auList);
        //设置列表的过期时间
        redisUtil.expire(apiListName, overtime, TimeUnit.MILLISECONDS);
        //生成密钥对
        Map map = RSAUtil.genKeyPair();
        //保存私钥到session
        request.getSession().setAttribute(CacheTag.PRIVATE, map.get(RSAUtil.PRIVATE));
        //保存公钥到session
        request.getSession().setAttribute(CacheTag.PUBLIC, map.get(RSAUtil.PUBLIC));
    }

    protected Cookie loginCache(String userId) {
        // 生成登录记录 保存到Redis 缓存 3天
        String token = UUID.randomUUID().toString();
        redisUtil.set(token, userId, loginCacheTime, TimeUnit.DAYS);
        //保存到cookie 3天 实现3天内同一客户端可自动登录
        Cookie cookie = new Cookie(CacheTag.CLIENT_LOGIN_TOKEN, token);
        cookie.setMaxAge(loginCacheTime * 24 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }

    protected String getLoginIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie loginCookie = null;
        for (Cookie cookie : cookies) {
            if ("INE".equals(cookie.getName()))
                loginCookie = cookie;
        }
        if (loginCookie == null)
            return null;
        String loginId = loginCookie.getValue();
        return loginId;
    }

    protected void saveLoginStatus(String userId) {
        /**
         *在redis注册用户的唯一ID 保存指定时间
         * */
        String uid = CacheTag.LOGIN_STATUS + userId;
        redisUtil.set(uid, userId, heartTime, TimeUnit.SECONDS);
    }

    /**
     * 删除原有的登录状态
     * 保存用户登录状态
     * 并生成一个加密的登录凭证
     * 登录凭证 = 密钥+redis key
     * */
    protected String saveUserStatus(User user) throws Exception {
        Set<String> keys = redisUtil.keys(CacheTag.SERVER_LOGIN_TOKEN + user.getMobile() + "*");
        if(keys.size()>0){
            redisUtil.delete(keys);
        }
        String uid = CacheTag.SERVER_LOGIN_TOKEN + user.getMobile() + PBEUtil.genSecretKey(8);
        redisUtil.set(uid, JSON.toJSONString(user), 1000, TimeUnit.SECONDS);
        String secretKey = PBEUtil.genSecretKey(6);
        String encrypt = PBEUtil.encrypt(uid, secretKey);
        return secretKey+encrypt;
    }

    protected String decodeToken(String str){
        try {
            String secretKey = str.substring(0, 6);
            String token = str.substring(6);
            token = URLDecoder.decode(token,"UTF-8");
            String decrypt = PBEUtil.decrypt(token, secretKey);
            return decrypt;
        } catch (Exception e) {
            return null;
        }
    }

    protected User getUserStatus(HttpServletRequest request) {
        String loginIdFromCookie = getLoginIdFromCookie(request);
        if (StringUtil.isNull(loginIdFromCookie))
            return null;
        try {
            String token = decodeToken(loginIdFromCookie);
            if(StringUtil.isNull(token))
                return null;
            String userString = redisUtil.get(token);
            User user = JSON.parseObject(userString, User.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
