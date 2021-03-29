package com.intellie.remoteApi.export.baidu;

import com.intellie.common.entity.system.Emap;
import com.intellie.common.utils.StringUtil;
import com.intellie.remoteApi.export.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/23 20:07
 * @describe:
 */
@RestController
@RequestMapping("face")
public class FaceController extends BaseController {

    @PostMapping("detection")
    public Map faceDetection(HttpServletRequest request, Emap em) {
        String imgStr = request.getParameter("imgStr");
        if (StringUtil.isNull(imgStr))
            return em.fail("参数错误");
        try {
            return faceApiService.faceDetectionService(imgStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("系统异常");
    }
}
