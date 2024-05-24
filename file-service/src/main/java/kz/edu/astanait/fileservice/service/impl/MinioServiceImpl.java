package kz.edu.astanait.fileservice.service.impl;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import kz.edu.astanait.fileservice.properties.MinioProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author aldi
 * @since 01.05.2024
 */
@RequiredArgsConstructor
@Service
public class MinioServiceImpl {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @SneakyThrows
    public void saveFile(InputStream inputStream, String filename) {
        try {
            createBucketIfNotExists();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(minioProperties.getBucket())
                .object(filename)
                .build());
    }

    @SneakyThrows
    private void createBucketIfNotExists() {
        var found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
        }
    }

    @SneakyThrows
    public InputStream getFileStream(String fileName) {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(minioProperties.getBucket())
                .object(fileName)
                .build());
    }

    @SneakyThrows
    public void removeFile(String filename) {
        minioClient.removeObject(
                RemoveObjectArgs.builder().bucket(minioProperties.getBucket()).object(filename).build());
    }
}
