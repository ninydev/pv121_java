package com.itstep.chat.models;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
