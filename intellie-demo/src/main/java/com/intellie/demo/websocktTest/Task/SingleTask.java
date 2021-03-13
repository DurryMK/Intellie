package com.intellie.demo.websocktTest.Task;

import com.alibaba.fastjson.JSON;
import com.intellie.demo.websocktTest.MessageBuilder;
import com.intellie.demo.websocktTest.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 15:17
 */
public class SingleTask implements Runnable {

    Logger logger = LoggerFactory.getLogger(SingleTask.class);

    private MessageBuilder builder;
    private String name;
    private boolean isRunning = false;

    public void init(MessageBuilder builder) {
        this.builder = builder;
    }

    public SingleTask(int name) {
        this.name = "Task-" + name;
    }

    public synchronized boolean getStatus(){
        return this.isRunning;
    }

    @Override
    public void run() {
        this.isRunning = true;
        long startTime = System.currentTimeMillis();
        logger.info("{} 开始发送消息", name);
        try {
            logger.info("{} ===> {} content:{}", builder.getOriginUser(),builder.getTargetUser(),builder.getMessage());
            MessageModel model = new MessageModel(builder);
            builder.getSocketSession().sendMessage(new TextMessage(JSON.toJSONString(model)));
        } catch (Exception e) {
            logger.info("{} 发送消息异常", name);
            e.printStackTrace();
        }finally {
            logger.info("{} 发送结束,耗时{} ms", name, System.currentTimeMillis() - startTime);
            this.isRunning = false;
        }

    }
}
