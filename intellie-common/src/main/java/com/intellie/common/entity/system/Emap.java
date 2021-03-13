package com.intellie.common.entity.system;

import java.util.HashMap;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/18 21:53
 * 请求响应结果的实体类
 */
public class Emap {

    private Map map;

    /**
     * 自定义响应码和返回内容
     */
    public Emap() {
        this.map = new HashMap();
    }

    /**
     * 自定义响应码和返回内容
     */
    public Emap(int code, Object obj) {
        this.map = new HashMap();
        this.map.put(code, obj);
    }

    /**
     * 返回无效状态
     */
    public Map invalid() {
        this.map = new HashMap<String, Object>();
        map.put(RES.TOKEN, RES.INVALID);
        map.put(RES.INFO, "无效的请求");
        return this.map;
    }

    /**
     * 返回异常状态
     */
    public Map exception() {
        this.map = new HashMap<String, Object>();
        map.put(RES.TOKEN, RES.EXCEPTION);
        map.put(RES.INFO, "系统异常");
        return this.map;
    }

    /**
     * 返回异常状态 自定义异常信息
     */
    public Map exception(String msg) {
        this.map = new HashMap<String, Object>();
        map.put(RES.TOKEN, RES.EXCEPTION);
        map.put(RES.INFO, msg);
        return this.map;
    }

    /**
     * 仅返回失败状态
     */
    public Map fail() {
        this.map = new HashMap<String, Object>();
        map.put(RES.TOKEN, RES.FAIL);
        return this.map;
    }

    /**
     * 仅返回成功状态
     */
    public Map success() {
        this.map = new HashMap<String, Object>();
        map.put(RES.TOKEN, RES.SUCCESS);
        return this.map;
    }

    /**
     * 返回成功状态 并传单个参数
     */
    public Map success(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(RES.TOKEN, RES.SUCCESS);
        this.map.put(RES.INFO, obj);
        return this.map;
    }

    /**
     * 返回成功状态 并传多个参数
     */
    public Map success(String[] keys, Object... objs) {
        this.map = new HashMap<String, Object>();
        this.map.put(RES.TOKEN, RES.SUCCESS);
        int index = 0;
        Map map1 = new HashMap<String, Object>();
        for (String key : keys) {
            map1.put(key, objs[index]);
            index++;
        }
        this.map.put(RES.INFO, map1);
        return this.map;
    }

    /**
     * 返回失败状态 并传递失败信息
     */
    public Map fail(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(RES.TOKEN, RES.FAIL);
        this.map.put(RES.INFO, obj);
        return this.map;
    }

    /**
     * 获取当前map的键
     */
    public String token() {
        return (String) this.map.get(RES.TOKEN);
    }

    /**
     * 获取当前map的值
     */
    public Object info() {
        return this.map.get(RES.INFO);
    }

    /**
     * 获取当前map的实体
     */
    public Map back() {
        return this.map;
    }
}
