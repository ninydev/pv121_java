package com.itstep.hello_spring.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("api/files")
public class FileUploadController {

    // Папка для загрузки файлов
    // private static final String UPLOAD_DIR = "/home/keeper/upload";
    // private static final String UPLOAD_DIR = "D:\\tmp\\upload";

    @Value("${upload.dir}")
    private String UPLOAD_DIR;


    @GetMapping("/get/{filename}")
    public ResponseEntity<Resource> getBodyByFileName(@PathVariable String filename) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename); // Это указывает браузеру на скачивание файла

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            // В случае, если файл не существует или не может быть прочитан, возвращаем 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }


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
