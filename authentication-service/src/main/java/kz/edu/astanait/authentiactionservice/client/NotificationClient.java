package kz.edu.astanait.authentiactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aldi
 * @since 06.06.2024
 */

@FeignClient(name = "notification-service", url = "${application.config.url.notification-service}")
public interface NotificationClient {
    @PostMapping("/sendToken")
    void sendToken(@RequestParam String token, @RequestParam String email);
}
