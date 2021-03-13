package com.intellie.user.export.base;

import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.utils.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/21 19:26
 */
@RestController
@RequestMapping("/acv")
public class ActiveController extends AbstractController {

    /**
     * 接收客户端定时发来的心跳信息
     * 用以维持用户的登录状态
     */
    @GetMapping("rt")
    public Map heart(HttpServletRequest request, Emap em) {
        String loginIdFromCookie = getLoginIdFromCookie(request);
        String token = decodeToken(loginIdFromCookie);
        if(StringUtil.isNull(loginIdFromCookie))
            return em.fail("未登录");
        if(!redisUtil.hasKey(token))
            return em.fail("未登录");
        redisUtil.expire(token,1000, TimeUnit.SECONDS);
        return em.success();
    }

}
