package com.intellie.sms.provider.impl;

import com.intellie.common.entity.system.Emap;
import com.intellie.sms.provider.service.SmsService;
import org.springframework.stereotype.Service;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 17:03
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public void sendVerifyCode(String mobile, String code, Emap em) {
        em.success();
    }
}
