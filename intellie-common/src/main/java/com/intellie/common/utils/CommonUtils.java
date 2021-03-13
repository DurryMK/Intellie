package com.intellie.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/11 20:37
 * @describe:公共工具类
 */
public class CommonUtils {
    public static String getDateString(long str){
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date(str));
    }
}


