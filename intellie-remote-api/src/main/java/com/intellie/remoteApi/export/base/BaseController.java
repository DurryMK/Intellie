package com.intellie.remoteApi.export.base;

import com.intellie.remoteApi.provider.service.FaceApiService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 12:21
 * @describe:
 */
public abstract class BaseController {
    @Autowired
    protected FaceApiService faceApiService;
}
