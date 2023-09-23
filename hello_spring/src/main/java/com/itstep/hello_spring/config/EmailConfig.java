package com.itstep.hello_spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.example.com"); // Укажите ваш SMTP-сервер
//        mailSender.setPort(587); // Укажите порт SMTP-сервера
//        mailSender.setUsername("your_email@example.com"); // Укажите вашу почту
//        mailSender.setPassword("your_password"); // Укажите пароль от почты

        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true"); // Установите в "true", чтобы увидеть отладочные сообщения

        return mailSender;
    }
}