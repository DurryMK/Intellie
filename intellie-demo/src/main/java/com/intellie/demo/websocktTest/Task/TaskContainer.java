package com.intellie.demo.websocktTest.Task;

import com.intellie.demo.websocktTest.MessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 15:14
 */
public class TaskContainer implements BaseContainer {

    private ConcurrentHashMap<Integer, SingleTask> container = null;
    Logger logger = LoggerFactory.getLogger(TaskContainer.class);

    @Override
    public TaskContainer init() {
        if (this.container == null) {
            container = new ConcurrentHashMap<>();
            for (int i = 0; i < 10; i++) {
                container.put(i, new SingleTask(i));
            }
            logger.info("TaskContainer init");
        }
        return this;
    }

    @Override
    public void executor(MessageBuilder builder) {
        Thread t;
        for (int i = 0; i < 10; i++) {
            SingleTask singleTask = this.container.get(i);
            if(!singleTask.getStatus()){
                singleTask.init(builder);
                t = new Thread(singleTask);
                t.start();
                return;
            }
        }
    }
}
