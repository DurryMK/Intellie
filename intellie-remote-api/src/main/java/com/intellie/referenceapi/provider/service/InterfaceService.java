package com.intellie.referenceapi.provider.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/1 9:10
 */
@Service
public interface InterfaceService {
    /**
     * 获取百度token
     */
    Map genBaiduToken();
}
