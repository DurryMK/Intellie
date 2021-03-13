package com.intellie.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 14:15
 * 自定义字符串工具
 */
public class CustomStringUtils {

    public static boolean isNull(String str) {
        if (str == null)
            return true;
        str = str.replace(" ", "");
        if (str.length() <= 0)
            return true;
        return false;
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static String getDateString(){
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
    }
}
