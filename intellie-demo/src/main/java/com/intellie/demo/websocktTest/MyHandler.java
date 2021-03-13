package com.intellie.demo.websocktTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intellie.demo.utils.CustomStringUtils;
import com.intellie.demo.websocktTest.Task.TaskContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 10:40
 */
public class MyHandler implements WebSocketHandler {
    Logger logger = LoggerFactory.getLogger(MyHandler.class);

    private Online oc = new Online();

    private TaskContainer container = new TaskContainer().init();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();
        if (CustomStringUtils.isNull(query)) {
            session.sendMessage(new TextMessage("参数异常"));
        }
        String name = query.substring(query.lastIndexOf("name=") + 5);
        oc.addOnlineCnt(name, session);
        logger.info("连接就绪 : {}    当前在线数：{}", name, oc.getCnt());
        logger.info("当前在线列表 ： ");
        for (String s : oc.getNameList()) {
            System.out.println(s);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> socketMessage) throws Exception {
        logger.info("接收到的消息 : {}", socketMessage.getPayload());
        //接收到的原始信息
        String msg = socketMessage.getPayload().toString();
        //信息Json对象
        JSONObject builderJson = JSON.parseObject(msg);
        String originUser = builderJson.getString("originUser");
        String message = builderJson.getString("message");
        //目标用户组
        JSONArray array = JSON.parseArray(builderJson.getString("targetUser"));

        List<MessageBuilder> builders = new ArrayList<>();

        String target;
        MessageBuilder builder;
        WebSocketSession socketSession;

        for (int i = 0; i < array.size(); i++) {
            target = array.getJSONObject(i).getString("name");
            socketSession = oc.getSession(target);
            if (socketSession != null) {
                builder = new MessageBuilder();
                builder.setCreateTime(CustomStringUtils.getDateString())
                        .setTargetUser(target)
                        .setOriginUser(originUser)
                        .setMessage(message)
                        .setSocketSession(socketSession);
                builders.add(builder);
            }
        }
        if (builders.size() <= 0) {
            return;
        }

        for (MessageBuilder m : builders){
            container.executor(m);
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
