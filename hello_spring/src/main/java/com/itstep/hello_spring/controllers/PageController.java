package com.itstep.hello_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(){
        return ("pages/index");
    }

    @GetMapping("/thymeleaf")
    public String buildServer(Model model){

        List<String> lstData = new ArrayList<>();
        lstData.add("Первый элемент");
        lstData.add("2 элемент");
        lstData.add("3 элемент");

        // Для передачи данных на фронт я использую контейнер
        // куда добавляю данные с помощью добалвения атрибутов
        model.addAttribute("lstData", lstData);

        model.addAttribute("title", "Hello Models");

        return ("example/thymeleaf");
    }
}
