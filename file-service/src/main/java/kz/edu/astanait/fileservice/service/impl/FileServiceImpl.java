package kz.edu.astanait.fileservice.service.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import kz.edu.astanait.fileservice.properties.MinioProperties;
import kz.edu.astanait.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author aldi
 * @since 26.04.2024
 */


// todo refactor
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public Boolean upload(MultipartFile file) {
        try {
            createBucketIfNotExists();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new RuntimeException("File upload failed");
        }
        String filename = generateFilename(file);

        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        saveFile(inputStream, filename);

        return true;
    }

    @SneakyThrows
    @Override
    public MultipartFile getFile() {
        return null;
    }

    @SneakyThrows
    private void saveFile(InputStream inputStream, String filename) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(minioProperties.getBucket())
                .object(filename)
                .build());
    }

    @SneakyThrows
    private void createBucketIfNotExists() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
        }
    }

    private String generateFilename(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID().toString() + "." + extension;
    }

    private String getExtension(MultipartFile file) {
        return file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
