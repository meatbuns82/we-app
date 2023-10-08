package com.luwh.we.app.message;

/**
 * 消息体
 *
 * @author lu.wh
 * @date 2023/10/07 18/26/49
 * @description
 */
public class MessageContent {
    private String id; // 消息id
    private String body; // 消息体

    private short type; // 消息类型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }
}
