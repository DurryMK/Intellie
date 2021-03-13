package com.intellie.demo.websocktTest;

import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/4 15:57
 *
 * 消息构建器
 */
public class MessageBuilder implements Serializable {
    private static final long serialVersionUID = -80154785684835908L;

    private String  originUser;//信息来源用户
    private String targetUser;//目标用户
    private WebSocketSession socketSession;
    private String message;//发送的信息内容
    private boolean verifyContent;//内容是否合法
    private String createTime;//服务器接收到消息的时间
    private String sendTime;//消息实际发送的时间
    private long delayTime;//createTime与sendTime的延迟时长

    public WebSocketSession getSocketSession() {
        return socketSession;
    }

    public void setSocketSession(WebSocketSession socketSession) {
        this.socketSession = socketSession;
    }

    public String getOriginUser() {
        return originUser;
    }

    public MessageBuilder setOriginUser(String originUser) {
        this.originUser = originUser;
        return this;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public MessageBuilder setTargetUser(String targetUser) {
        this.targetUser = targetUser;
        return this;
    }




    public String getMessage() {
        return message;
    }

    public MessageBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isVerifyContent() {
        return verifyContent;
    }

    public MessageBuilder setVerifyContent(boolean verifyContent) {
        this.verifyContent = verifyContent;
        return this;
    }


    public MessageBuilder setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public MessageBuilder setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public MessageBuilder setDelayTime(long delayTime) {
        this.delayTime = delayTime;
        return this;
    }


    @Override
    public String toString() {
        return "MessageBuilder{" +
                "originUser='" + originUser + '\'' +
                ", targetUser='" + targetUser + '\'' +
                ", socketSession=" + socketSession +
                ", message='" + message + '\'' +
                ", verifyContent=" + verifyContent +
                ", createTime='" + createTime + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }

    public String getCreateTime() {
        return createTime;
    }

}
