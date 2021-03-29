package com.intellie.user.export.info;

import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.utils.StringUtil;
import com.intellie.user.entity.user.UserPaperType;
import com.intellie.user.export.base.AbstractController;
import com.intellie.common.entity.user.User;
import com.intellie.common.entity.user.UserDetail;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/13 17:57
 * @describe:获取用户信息的控制类
 */
@RestController
@RequestMapping("/info")
public class UserInfoController extends AbstractController {

    /**
     *获取用户的详细信息
     * */
    @GetMapping("getUserInfo")
    public Map getUserInfo(HttpServletRequest request, Emap em) {
        User userStatus = getUserStatus(request);
        if(userStatus == null)
            return em.fail("未登录");
        UserDetail userInfo = null;
        try {
            userInfo = userService.getUserDetailInfo(userStatus);
            return em.success(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("未找到用户");
        }
    }

    /**
     *获取用户设定的试卷类型列表
     * */
    @GetMapping("getUserPaperTypeList")
    public Map getUserPaperTypeList(HttpServletRequest request, Emap em) {
        User userStatus = getUserStatus(request);
        if(userStatus == null)
            return em.fail("未登录");
        try {
            List<UserPaperType> userPaperTypeList = userService.getUserPaperTypeList(userStatus.getId());
            return em.success(userPaperTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }

    /**
     *获取考生的信息
     * */
    @GetMapping("getExamUserInfo")
    public Map getExamUserInfo(HttpServletRequest request, Emap em) {
        String mobile = request.getParameter("mobile");
        if(StringUtil.isNull(mobile)||mobile.length()<11)
            return em.fail("用户不存在");
        try {
            User user = new User();
            user.setMobile(mobile);
            UserDetail userDetailInfo = userService.getUserDetailInfo(user);
            if(!"1".equals(userDetailInfo.getrName()))
                return em.fail("未实名");
            return em.success(new String[]{"name","id","school"},userDetailInfo.getRealName(),userDetailInfo.getIdCard(),userDetailInfo.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("未找到用户");
        }
    }


}
