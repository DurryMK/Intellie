package com.intellie.demo.demo210303;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/21 19:26
 */
@RestController
@RequestMapping("/test1")
public class TTController {

    @Resource
    private UserDao dao;

    @GetMapping("test11")
    public Object initHea(String s) {
        return dao.testQuery(s);
    }

}
