package com.itstep.cursova_v1.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadServiceInterface {

    public String uploadFile(String bucketName,  MultipartFile uploadFile);
}
