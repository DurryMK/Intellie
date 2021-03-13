package com.intellie.sms.export;

import com.intellie.common.entity.system.Emap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 16:29
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    private Logger log = LoggerFactory.getLogger(EmailController.class);

    @GetMapping("test")
    public Map getUserInfo(HttpServletRequest request, Emap em, String str) {
        Object emailUser = request.getSession().getAttribute(str);
        if (emailUser == null) {
            request.getSession().setAttribute(str, str);
            return em.success("已经创建用户");
        } else {
            return em.success(emailUser);
        }
    }

    @GetMapping("setCookie")
    public Map qwe(HttpServletRequest request, Emap em, String str, HttpServletResponse response) {
        Cookie cookie = new Cookie(str,str);
        cookie.setPath("/");
        cookie.setMaxAge(30);
        response.addCookie(cookie);
        return em.success(str);
    }

    @GetMapping("getCookie")
    public Map qasdwe(HttpServletRequest request, Emap em, String str, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (str.equals(cookie.getName())) {
                    str = cookie.getValue();
                }
            }
        } else {
            return em.fail("无"+str+"记录");
        }
        return em.success(str);
    }
}
