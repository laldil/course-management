package kz.edu.astanait.courseservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aldi
 * @since 06.06.2024
 */
@FeignClient(name = "notification-service", url = "${application.config.url.notification-service}")
public interface NotificationClient {
    @PostMapping("/sendTgMessage")
    void sendTgMessage(@RequestParam Long chatId, @RequestParam String message);
}
