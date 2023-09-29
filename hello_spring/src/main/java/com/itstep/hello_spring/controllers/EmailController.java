package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.services.helpers.email.EmailJobService;
import com.itstep.hello_spring.services.helpers.email.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    final EmailService emailService;
    final EmailJobService emailJobService;


    public EmailController(EmailService emailService, EmailJobService emailJobService) {
        this.emailService = emailService;
        this.emailJobService = emailJobService;
    }

    @GetMapping("/send")
    public void send(){
        String cratedAt = (new Date()).toString();
        emailService.sendEmail("keeper@ninydev.com", "Test Send", " CratedAt: " + cratedAt + "\n");
    }

    @GetMapping("/job")
    public void job(){
        String cratedAt = (new Date()).toString();
        emailJobService.pushEmailJob("keeper@ninydev.com", "Test Job", " CratedAt: " + cratedAt + "\n");
    }
}
