package kz.edu.astanait.scoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class ScoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoreServiceApplication.class, args);
    }

}
