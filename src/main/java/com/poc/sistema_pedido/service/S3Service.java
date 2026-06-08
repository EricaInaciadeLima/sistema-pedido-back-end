package com.poc.sistema_pedido.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
public class S3Service {

    private final S3Client s3Client;

    private final String bucketName;
    private final String s3Endpoint;

    public S3Service(
            S3Client s3Client,
            @Value("${app.s3.bucket:produtos}") String bucketName,
            @Value("${app.s3.endpoint:http://localhost:4566}") String s3Endpoint
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.s3Endpoint = s3Endpoint;
    }

    public String upload(MultipartFile file, String key) {

        try (InputStream inputStream = file.getInputStream()) {

            System.out.println("📤 Upload iniciando: " + key);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(inputStream, file.getSize())
            );

            String url = normalizeEndpoint(s3Endpoint) + "/" + bucketName + "/" + key;

            System.out.println("✅ Upload concluído: " + url);

            return url;

        } catch (Exception e) {

            e.printStackTrace(); // 👈 IMPORTANTE pra debug

            throw new RuntimeException("Erro ao fazer upload: " + e.getMessage(), e);
        }
    }

    private String normalizeEndpoint(String endpoint) {
        if (endpoint == null || endpoint.isBlank()) {
            return "http://localhost:4566";
        }

        return endpoint.endsWith("/") ? endpoint.substring(0, endpoint.length() - 1) : endpoint;
    }
}
