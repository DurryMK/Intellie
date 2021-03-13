package com.intellie.referenceapi.provider.impl;

import com.intellie.common.entity.system.RES;
import com.intellie.referenceapi.provider.remoteInterface.iBaidu.GenBaiduToken;
import com.intellie.referenceapi.provider.service.InterfaceService;
import com.intellie.referenceapi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/1 9:11
 */
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    private RedisUtil redisUtil;

    private String BAIDU_TOKEN = "BAIDU_TOKEN";
    @Override
    public Map genBaiduToken() {
        Map<String, String> resultMap = new HashMap<>();
        String token = "";
        try {
            if (redisUtil.hasKey(BAIDU_TOKEN)) {
                //从redis读取百度token
                token = redisUtil.get(BAIDU_TOKEN);
            } else {
                //如果没有或者已经过期  则重新拉取token
                token = GenBaiduToken.getAuth();
                //存入redis 缓存25天
                redisUtil.set(BAIDU_TOKEN, token);
                redisUtil.expire(BAIDU_TOKEN, 25, TimeUnit.DAYS);
            }
            resultMap.put(RES.TOKEN, RES.SUCCESS);
        } catch (Exception e) {
            resultMap.put(RES.TOKEN, RES.FAIL);
            e.printStackTrace();
        }
        resultMap.put(BAIDU_TOKEN, token);
        return resultMap;
    }
}
