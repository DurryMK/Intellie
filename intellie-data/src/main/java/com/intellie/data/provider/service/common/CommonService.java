package com.intellie.data.provider.service.common;

import com.intellie.common.entity.user.User;
import com.intellie.data.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:06
 * @describe:公用服务
 */
@Service
public interface CommonService {
    String getLoginIdFromCookie(HttpServletRequest request);
    String decodeLoginToken(HttpServletRequest request);
    User getLoginStatus(HttpServletRequest request);
}
