package com.intellie.user.provider.service;

import com.intellie.common.entity.system.Emap;
import org.springframework.stereotype.Service;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 10:00
 */
@Service
public interface AccessService {
    void quickLogin(String mobile, String vcode, Emap em);

    void autoLogin(String token, Emap em);

    void normalLogin(String token, String pwd, Emap em);
}
