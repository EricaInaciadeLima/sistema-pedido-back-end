package com.poc.sistema_pedido.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;

@Service
public class S3Service {

    private final S3Client s3Client;

    private final String bucketName = "produtos";

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
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

            String url = "http://localhost:4566/" + bucketName + "/" + key;

            System.out.println("✅ Upload concluído: " + url);

            return url;

        } catch (Exception e) {

            e.printStackTrace(); // 👈 IMPORTANTE pra debug

            throw new RuntimeException("Erro ao fazer upload: " + e.getMessage(), e);
        }
    }
}