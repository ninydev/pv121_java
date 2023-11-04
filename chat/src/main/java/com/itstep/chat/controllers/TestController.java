package com.itstep.chat.controllers;

import com.itstep.chat.models.ChatMessage;
import com.itstep.chat.services.WebSocketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    final WebSocketService socketService;
    public TestController(WebSocketService socketService){
        this.socketService =socketService;
    }
    @GetMapping("/api/test")
    public String test() {
        // Номер моей задачи я возвращаю через http запрос
        UUID id = UUID.randomUUID();

        ChatMessage msg = new ChatMessage();
        msg.setSender("Сервис вася закончил свою работу");
        msg.setContent(" Работа  " + id.toString() + "Выполнена");

        socketService.sendMessageToTopic(msg);

        return id.toString();
    }
}
