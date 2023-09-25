package com.itstep.hello_spring.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("api/files")
public class FileUploadController {

    // Папка для загрузки файлов
    // private static final String UPLOAD_DIR = "/home/keeper/upload";
    // private static final String UPLOAD_DIR = "D:\\tmp\\upload";

    @Value("${upload.dir}")
    private String UPLOAD_DIR;


    @PostMapping("/upload")
    public String upload(
            @RequestParam("file")MultipartFile uploadFile
            ) throws IOException {
        // Проверяем - а прислал ли нам пользователь файл вообще
        if( uploadFile.isEmpty()) {
            return " No File in Request";
        }

        // Проверяем - есть ли каталог для сохранения файла
        Path uploadDir= Path.of(UPLOAD_DIR);
        Files.createDirectories(uploadDir);

        // Сохраним файл в нужной нам дирректории
        // - Настроим полный путь к месту хранения файла
        Path filePath = uploadDir.resolve(uploadFile.getOriginalFilename());
        // - Собственно говоря - само копирование файла
        Files.copy(uploadFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


        return "Ok";
    }

}
