package com.intellie.sms.utils;

import java.util.Random;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/15 16:27
 *
 */
public class SmsUtil {
    public static String GenVerifyCode(long length) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
