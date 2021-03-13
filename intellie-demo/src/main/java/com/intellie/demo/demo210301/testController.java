package com.intellie.demo.demo210301;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/1 11:20
 */
@RestController()
@RequestMapping("/test")
public class testController {
    @RequestMapping("/tt1")
    public Map tt1(){
        Map res = new HashMap<String,String>();
        res.put("hello","durry");
        return res;
    }
}
