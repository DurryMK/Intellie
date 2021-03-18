package com.intellie.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 14:15
 * 自定义字符串工具
 */
public class StringUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    public static int limitTime(String startTime, String endTime) throws ParseException {
        Date start = simpleDateFormat.parse(startTime);
        Date end = simpleDateFormat.parse(endTime);
        Date now = new Date(System.currentTimeMillis());
        return now.getTime() <= start.getTime() ? -1 : (now.getTime() <= end.getTime()) ? 0 : 1;
    }

    public static void main(String[] args) throws ParseException {
        String start = "2021-3-16 08:00:00";
        String end = "2021-03-1 11:00:00";
        System.out.println(limitTime(start,end));
    }

}
