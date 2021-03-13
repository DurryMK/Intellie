package com.intellie.sms.provider.actuator.base;

import com.intellie.sms.provider.actuator.base.Actuator;

import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/4 17:29
 */
public abstract class BaseActuator implements Actuator {
    protected Map<String,Object> context;

    protected Actuator addParams(String key,Object value){
        this.context.put(key,value);
        return this;
    }

    protected Map getContent(){
        return this.context;
    }

    protected Object getParam(String key){
        return this.context.get(key);
    }
}
