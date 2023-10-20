package com.itstep.cursova_v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {

    @Autowired
    EmailService emailService;


    public void alarm(String msg) {
        emailService.sendEmail("keeper@ninydev.com", "Alarm: Error in App", msg);

    }

}
