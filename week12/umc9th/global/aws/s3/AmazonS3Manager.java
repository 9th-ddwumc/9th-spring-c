package com.example.umc9th.global.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.umc9th.domain.uuid.entity.Uuid;
import com.example.umc9th.domain.uuid.repository.UuidRepository;
import com.example.umc9th.global.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e){
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public String generateReviewKeyName(Uuid uuid) {
        return amazonConfig.getReviewPath() + '/' + uuid.getUuid();
    }

    public void deleteFile(String fileUrl) {
        String key = extractKeyFromUrl(fileUrl);

        amazonS3.deleteObject(
                new DeleteObjectRequest(
                        amazonConfig.getBucket(),
                        key
                )
        );

        // log.info("S3 file deleted. key={}", key);
    }

    private String extractKeyFromUrl(String fileUrl) {
        // 예: https://bucket.s3.ap-northeast-2.amazonaws.com/review/uuid
        int idx = fileUrl.indexOf(".amazonaws.com/");
        if (idx == -1) {
            throw new IllegalArgumentException("Invalid S3 file URL");
        }
        return fileUrl.substring(idx + ".amazonaws.com/".length());
    }


}