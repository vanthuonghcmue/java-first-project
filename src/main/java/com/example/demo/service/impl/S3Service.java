package com.example.demo.service.impl;

import com.example.demo.config.AWSConfig;
import com.example.demo.service.IS3Service;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@Log
public class S3Service implements IS3Service {

    final AWSConfig awsConfig;

    public S3Service(AWSConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    @Override
    public String generatePreSignUrlUploadAvatar(String extension) {
        try (S3Presigner presigner = awsConfig.s3Presigner()) {

            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(awsConfig.getBucket())
                    .key(UUID.randomUUID() + "." + extension)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(awsConfig.getPreSignedUrlTtlInMinute()))  // The URL expires in 10 minutes.
                    .putObjectRequest(objectRequest)
                    .build();


            PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);

            URL myURL = presignedRequest.url();
            String string = myURL.toString();
            log.info("Presigned URL: " + string);
            log.info("HTTP method: " + presignedRequest.httpRequest().method());

            return string;
        }
    }

    public String generateGetObjectPresignURL(String objectKey) {
        try (S3Presigner presigner = awsConfig.s3Presigner()) {

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(awsConfig.getBucket())
                    .key(objectKey)
                    .build();

            GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(awsConfig.getPreSignedUrlTtlInMinute()))
                    .getObjectRequest(getObjectRequest)
                    .build();

            PresignedGetObjectRequest presignedGetObjectRequest = presigner.presignGetObject(getObjectPresignRequest);

            String theUrl = presignedGetObjectRequest.url().toExternalForm();
            log.info("Presigned URL: " + theUrl);
            return theUrl;
        }

    }
}
