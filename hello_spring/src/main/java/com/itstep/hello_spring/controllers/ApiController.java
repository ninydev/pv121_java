package com.itstep.hello_spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/test")
    public String test() {
        return "Hello World";
    }
}
