package com.itstep.cursova_v1.controllers;

import com.itstep.cursova_v1.services.AzureVisionService;
import com.itstep.cursova_v1.services.ChatGPTService;
import com.itstep.cursova_v1.services.LocalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class LyricFromImageController {

    @Autowired
    private LocalFileService localFileService;
    @Autowired
    private AzureVisionService azureVisionService;

    @Autowired
    private ChatGPTService chatGPTService;
    @PostMapping("/upload")
    public String upload(
            @RequestParam("file") MultipartFile uploadFile
    ) throws IOException,  NoSuchAlgorithmException, InvalidKeyException {
        // Проверяем - а прислал ли нам пользователь файл вообще
        if (uploadFile.isEmpty()) {
            return " No File in Request";
        }

        String filePath = localFileService.uploadFile("ai", uploadFile);
        String question = azureVisionService.analyzeImageFromFile(filePath);
        String lyric = chatGPTService.SendQuestion(question + "\n на русском языке");
        return lyric;
    }
}
