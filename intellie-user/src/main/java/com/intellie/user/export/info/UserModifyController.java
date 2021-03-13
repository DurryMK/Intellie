package com.intellie.user.export.info;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.common.entity.user.UserDetail;
import com.intellie.common.utils.StringUtil;
import com.intellie.user.entity.user.UserPaperType;
import com.intellie.user.export.base.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/12 19:02
 * @describe:修改用户信息的控制类
 */
@RestController
@RequestMapping("/modify")
public class UserModifyController extends AbstractController {
    /**
     *获取用户的详细信息
     * */
    @PostMapping("addUserPaperType")
    public Map addUserPaperType(HttpServletRequest request, Emap em) {
        User userStatus = getUserStatus(request);
        if(userStatus == null)
            return em.fail("未登录");
        String newType = request.getParameter("newType");
        if(StringUtil.isNull(newType))
            return em.fail("添加失败");
        try {
            List<UserPaperType> userPaperTypeList = userService.getUserPaperTypeList(userStatus.getId());
            for(UserPaperType paperType: userPaperTypeList){
                if (newType.equals(paperType.getName()))
                    return em.fail("已有该类型");
            }
            userService.addUserPaperType(userStatus.getId(),newType);
            return em.success();
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("未找到用户");
        }
    }
}
