package com.intellie.demo.websocktTest.actuator.base;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/4 17:33
 * 执行器的父类
 */
public interface Actuator {
    void init();
    void execute();
    void destroy();
}
