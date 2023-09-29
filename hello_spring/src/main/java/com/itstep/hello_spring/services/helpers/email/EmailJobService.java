package com.itstep.hello_spring.services.helpers.email;

import com.itstep.hello_spring.jobs.EmailJob;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Серивс постановки в очередь почтовых сообщений
 */
@Service
public class EmailJobService {

    private final EmailService emailService;

    public EmailJobService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async("emailExecutor")
    public void pushEmailJob(String to, String subject, String text){
        EmailJob job = new EmailJob(emailService, to, subject, text);
        job.run();
    }
}
