package kz.edu.astanait.authentiactionservice.configuration;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aldi
 * @since 02.06.2024
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
