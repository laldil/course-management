package kz.edu.astanait.courseservice.configuration;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aldi
 * @since 10.04.2024
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}