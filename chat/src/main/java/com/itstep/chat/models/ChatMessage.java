package com.itstep.chat.models;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
}
