package com.intellie.demo.websocktTest;

import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/5 17:06
 */
public class MessageModel implements Serializable {
    private static final long serialVersionUID = -146110914563411910L;
    private String  originUser;//信息来源用户
    private String message;//发送的信息内容
    private String sendTime;//消息实际发送的时间

    public MessageModel(MessageBuilder builder) {
        this.originUser = builder.getOriginUser();
        this.message = builder.getMessage();
        this.sendTime = builder.getCreateTime();
    }

    public String getOriginUser() {
        return originUser;
    }

    public void setOriginUser(String originUser) {
        this.originUser = originUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "originUser='" + originUser + '\'' +
                ", message='" + message + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
