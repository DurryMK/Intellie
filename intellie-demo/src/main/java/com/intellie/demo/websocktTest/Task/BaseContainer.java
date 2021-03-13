package com.intellie.demo.websocktTest.Task;

import com.intellie.demo.websocktTest.MessageBuilder;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 15:16
 */
public interface BaseContainer {
    TaskContainer init();
    void executor(MessageBuilder builder);
}
