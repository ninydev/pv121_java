package com.itstep.hello_spring.services.helpers;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioFileService {

    private final MinioClient minioClient;

    public MinioFileService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void uploadFile(String bucketName,  MultipartFile uploadFile) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {

        boolean found =
                minioClient.bucketExists(
                        BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            // Make a new bucket called bucketName
            minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            System.out.println("Bucket " + bucketName +  " already exists.");
        }

        try {

            File temp = new File(uploadFile.getOriginalFilename());
            temp.canWrite();
            temp.canRead();

            Files.copy(uploadFile.getInputStream(), temp.toPath());

            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(uploadFile.getOriginalFilename())
                    .filename(temp.getAbsolutePath())
                    .build();

            minioClient.uploadObject(
                    uploadObjectArgs);

            temp.delete();



        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}