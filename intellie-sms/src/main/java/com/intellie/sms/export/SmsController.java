package com.intellie.sms.export;

import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.system.RES;
import com.intellie.common.utils.StringUtil;
import com.intellie.sms.provider.service.SmsService;
import com.intellie.sms.utils.RedisUtil;
import com.intellie.sms.utils.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 16:29
 */
@RestController
@RequestMapping("/sendSms")
public class SmsController {

    private Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SmsService smsService;

    //验证码过期时间 单位分钟
    private final long expire = 1;

    //验证码长度
    private final long verifyCodeLength = 6;

    //资源锁过期时间
    private final long lockExpire = 60;

    private final String LOCK = "SMSLOCK_";

    @GetMapping("sendVerifyCode")
    public Map getUserInfo(HttpServletRequest request, Emap em) {
        String mobile = request.getParameter("mobile");
        if (StringUtil.isNull(mobile)) {
            return em.fail("手机号格式错误");
        }
        String lock = LOCK + mobile;
        String token = CacheTag.VERIFY_TOKEN + mobile;
        boolean doLock = false;
        if (redisUtil.doLock(lock,lockExpire)) {
            try {
                doLock = true;
                if (redisUtil.hasKey(token)) {
                    return em.fail(String.format("验证码%s分钟内有效，请勿频繁发送。", expire));
                }
                //生成一个验证码
                String verifyCode = SmsUtil.GenVerifyCode(verifyCodeLength);
                log.debug("{}的验证码为:{}", mobile, verifyCode);
                smsService.sendVerifyCode(mobile, verifyCode, em);
                if (!RES.SUCCESS.equals(em.token())) {
                    return em.back();
                }
                redisUtil.set(token, verifyCode, expire, TimeUnit.MINUTES);
                return em.success(verifyCode);
            } finally {
                if (doLock) {
                    redisUtil.removeLock(lock);
                }
            }
        } else {
            return em.fail("操作过于频繁");
        }
    }
}
