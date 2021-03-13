package com.intellie.sms.provider.service;

import com.intellie.common.entity.system.Emap;
import org.springframework.stereotype.Service;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 16:56
 */
@Service
public interface SmsService {
    void sendVerifyCode(String mobile, String code, Emap em);
}
