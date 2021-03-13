package com.intellie.demo.websocktTest;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 9:42
 */
public class Online {

    private ConcurrentHashMap<String, WebSocketSession> ch = new ConcurrentHashMap<>();


    public synchronized int getCnt(){
        return this.ch.size();
    }

    public synchronized int reduceOnlineCnt(String name){
        if(ch.containsKey(name)){
            ch.remove(name);
        }
        return this.ch.size();
    }

    public synchronized int addOnlineCnt(String name , WebSocketSession session){
        ch.put(name,session);
        return this.ch.size();
    }

    public synchronized List<String> getNameList(){
        Enumeration<String> keys = this.ch.keys();
        List<String> list = new ArrayList<>();
        while(keys.hasMoreElements()){
            list.add(keys.nextElement());
        }
        return list;
    }

    public synchronized WebSocketSession getSession(String name){
        return this.ch.get(name);
    }

}
