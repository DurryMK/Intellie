package com.intellie.demo.demo210303;

import com.intellie.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/11 18:31
 * @describe:
 */
public class som {
    public static void main(String[] args) {
        t t = new t();
        t.init();
        t.say();
        t t8 = new t();
        t8.say();
    }
}
class t{
    private static int i = 0;

    public void init(){
        t.i = 22;
    }
    public void say(){
        System.out.println(i);
    }
}