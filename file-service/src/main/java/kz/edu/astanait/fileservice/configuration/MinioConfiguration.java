package kz.edu.astanait.fileservice.configuration;

import io.minio.MinioClient;
import kz.edu.astanait.fileservice.properties.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aldi
 * @since 24.04.2024
 */
@RequiredArgsConstructor
@Configuration
public class MinioConfiguration {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
