package com.itstep.hello_spring.services.helpers;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioFileService {

    private final MinioClient minioClient;

    public MinioFileService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void uploadFile(String bucketName, String objectName, Path filePath) throws MinioException {
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(filePath.getFileName().toString())
                            .build());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}