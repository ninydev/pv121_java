package com.itstep.hello_spring.models.chat;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
}
