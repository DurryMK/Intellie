package com.intellie.remoteApi.provider.impl.iBaidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.system.RES;
import com.intellie.remoteApi.provider.impl.util.GenBaiduToken;
import com.intellie.remoteApi.provider.impl.util.GsonUtils;
import com.intellie.remoteApi.provider.impl.util.HttpUtil;
import com.intellie.remoteApi.provider.service.FaceApiService;
import com.intellie.remoteApi.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/23 11:18
 * @describe:
 */
@Service
public class FaceApiServiceImpl implements FaceApiService {
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 百度接口请求TOKEN缓存键
     */
    public final String Baibu_TOKEN = "BAIDU_TOKEN";
    /**
     * 百度人脸对比api请求地址
     */
    public final String Baibu_FaceCompare = "https://aip.baidubce.com/rest/2.0/face/v3/match";

    /**
     * 百度人脸检测api请求地址
     */
    public final String Baibu_Detection = "https://aip.baidubce.com/rest/2.0/face/v3/detect";

    private Emap em = new Emap();

    @Override
    public Map faceDetectionService(String imgStr) {
        try {
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("image", imgStr);//图片的BASE64编码
            requestMap.put("face_field", "quality");//quality用于识别图片质量是否符合人脸识别的要求
            requestMap.put("image_type", "BASE64");//图片格式BASE64

            String param = GsonUtils.toJson(requestMap);//转为JOSN格式

            // 获取接口token
            Map tokenMap = getBaiDuToken();
            if (!tokenMap.get(RES.TOKEN).equals(RES.SUCCESS))
                return em.fail("图像采集失败");
            String accessToken = (String) tokenMap.get(RES.INFO);
            //调用接口获取返回值
            String RESresult = HttpUtil.post(Baibu_Detection, accessToken, "application/json", param);
            //解析结果返回
            return parseDetection(RESresult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return em.fail("图像采集失败");
    }

    private Map parseDetection(String RESresult) {
        //JSON解析
        JSONObject jsonObject = JSON.parseObject(RESresult);
        //是否成功的标识 SUCCESS
        String error_msg = jsonObject.getString("error_msg");
        if (StringUtils.isEmpty(error_msg) || !error_msg.equals("SUCCESS"))
            return em.fail("图像处理错误");
        //检测结果
        JSONObject RESultJson = jsonObject.getJSONObject("RESult");
        //人脸数量
        double face_num = Double.parseDouble(RESultJson.getString("face_num"));
        if (face_num == 0) {
            return em.fail("未检测到人脸");
        } else if (face_num > 1) {
            return em.fail("人数过多");
        } else {
            /**
             *left_eye : 0.6, #左眼被遮挡的阈值
             * right_eye : 0.6, #右眼被遮挡的阈值
             * nose : 0.7, #鼻子被遮挡的阈值
             * mouth : 0.7, #嘴巴被遮挡的阈值
             * left_cheek : 0.8, #左脸颊被遮挡的阈值
             * right_cheek : 0.8, #右脸颊被遮挡的阈值
             * chin_contour : 0.6, #下巴被遮挡阈值
             * blur : 取值范围[0~1]，0是最清晰，1是最模糊	小于0.7
             * illumination : 取值范围[0~255],脸部光照的灰度值，0表示光照不好,以及对应客户端SDK中，YUV的Y分量 大于40
             * completeness :（0或1），0为人脸溢出图像边界，1为人脸都在图像边界内
             * */
            //照片质量评估
            JSONObject quality = RESultJson.getJSONArray("face_list").getJSONObject(0).getJSONObject("quality");
            //光照程度
            double illumination = quality.getDouble("illumination");
            //模糊程度
            double blur = quality.getDouble("blur");
            //完整程度
            double completeness = quality.getDouble("completeness");
            //五官遮挡程度
            JSONObject occlusion = quality.getJSONObject("occlusion");

            if (illumination < 30) {
                return em.fail("光线过暗");
            } else if (completeness != 1) {
                return em.fail("请保持面部在采集框范围内");
            } else if (blur < 0.6) {
                return em.fail("请保持图像稳定");
            } else if (occlusion.getDouble("right_eye") > 0.6 || occlusion.getDouble("left_eye") > 0.6 ||
                    occlusion.getDouble("nose") > 0.7 || occlusion.getDouble("mouth") > 0.7 ||
                    occlusion.getDouble("left_cheek") > 0.8 || occlusion.getDouble("right_cheek") > 0.8 ||
                    occlusion.getDouble("chin_contour") > 0.6) {
                return em.fail("请保持面部清晰无遮挡");

            }
        }
        return em.fail("图像采集成功");
    }

    @Override
    public Map faceCompareService(String thisImg, String targetImg) {
        return null;
    }

    @Override
    public Map getBaiDuToken() {
        String token = "";
        if (redisUtil.hasKey(Baibu_TOKEN)) {
            //从redis读取百度token
            token = redisUtil.get(Baibu_TOKEN);
        } else {
            //如果没有或者已经过期  则重新拉取token
            token = GenBaiduToken.getAuth();
            //存入redis 缓存25天
            redisUtil.set(Baibu_TOKEN, token, 25, TimeUnit.DAYS);
        }
        return em.success(token);
    }
}
