package com.intellie.user.export.login;

import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.system.RES;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.StringUtil;
import com.intellie.user.export.base.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 9:49
 */
@RestController
@RequestMapping("/access")
public class AccessController extends AbstractController {

    @GetMapping("/initApp")
    public Map initApp(HttpServletRequest request, Emap em) {
        String loginIdFromCookie = getLoginIdFromCookie(request);
        return StringUtil.isNull(loginIdFromCookie) ? em.fail("未登录") : em.success();
    }

    @PostMapping("/quickLogin")
    public Map quickLogin(HttpServletRequest request, Emap em, HttpServletResponse response) throws NoSuchAlgorithmException {
        String mobile = request.getParameter("mobile");
        String vcode = request.getParameter("vcode");
        //快速登录
        accessService.quickLogin(mobile, vcode, em);
        if (RES.SUCCESS.equals(em.token())) {
            try {
                //登录成功
                User user = (User) em.info();
                String loginId = this.saveUserStatus(user);
                return em.success(loginId);
            } catch (Exception e) {
                e.printStackTrace();
                return em.fail("登陆失败");
            }
        }
        return em.back();
    }

    @PostMapping("/normalLogin")
    public Map normalLogin(HttpServletRequest request, Emap em, HttpServletResponse response) throws Exception {
        String token = request.getParameter("token");
        String pwd = request.getParameter("password");

        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(pwd)) {
            return em.fail("登录失败");
        }

        accessService.normalLogin(token, pwd, em);
        if (RES.SUCCESS.equals(em.token())) {
            try {
                //登录成功
                User user = (User) em.info();
                String loginId = this.saveUserStatus(user);
                return em.success(loginId);
            } catch (Exception e) {
                e.printStackTrace();
                return em.fail("登陆失败");
            }
        }
        return em.back();
    }
    @GetMapping("/exit")
    public Map exit(HttpServletRequest request, Emap em, HttpServletResponse response) throws NoSuchAlgorithmException {
        String loginIdFromCookie = getLoginIdFromCookie(request);
        if (StringUtil.isNotNull(loginIdFromCookie)){
            redisUtil.delete(loginIdFromCookie);
        }
        return em.success();
    }
    @PostMapping("/autoLogin")
    public Map autoLogin(HttpServletRequest request, Emap em, HttpServletResponse response) throws NoSuchAlgorithmException {
        String token = null;
        //取出Cookie中的登录token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CacheTag.CLIENT_LOGIN_TOKEN.equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        } else {
            return em.fail("无登录记录");
        }
        if (StringUtils.isEmpty(token)) {
            return em.fail("登录凭证为空");
        }
        //自动登录
        accessService.autoLogin(token, em);
        if (!RES.SUCCESS.equals(em.token())) {
            return em.back();
        }
        //登录成功
        String userId = (String) em.info();
        loginHandle(request, response, userId);
        return em.success(request.getSession().getAttribute(CacheTag.PUBLIC));
    }

    private void loginHandle(HttpServletRequest request, HttpServletResponse response, String userId) throws NoSuchAlgorithmException {
        //登录成功
        //用户id保存到session
        request.getSession().setAttribute(CacheTag.SERVER_LOGIN_TOKEN, userId);
        //生成一个自动登录的token，保存到客户端cookie和redis
        Cookie cookie = this.loginCache(userId);
        response.addCookie(cookie);
        //为当前登录用户生成一个api访问token列表 生成密钥对
        this.apiTokenHandle(request);
        //用户的登录信息保存到redis
        this.saveLoginStatus(userId);
    }
}
