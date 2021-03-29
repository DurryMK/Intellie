package com.intellie.remoteApi.provider.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/23 11:03
 * @describe:百度人脸识别服务
 */
@Service
public interface FaceApiService {
    /**
     * 人脸识别
     */
    Map faceDetectionService(String imgStr);

    /**
     * 人脸对比
     */
    Map faceCompareService(String thisImg, String targetImg);

    Map getBaiDuToken();

}
