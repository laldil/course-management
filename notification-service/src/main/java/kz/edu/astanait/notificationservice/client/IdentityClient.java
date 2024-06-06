package kz.edu.astanait.notificationservice.client;

import kz.edu.astanait.notificationservice.dto.LinkTgRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aldi
 * @since 06.06.2024
 */
@FeignClient(name = "authentication-service", url = "${application.config.url.authentication-service}")
public interface IdentityClient {
    @PostMapping("/linkTg")
    void linkTg(@RequestParam Long chatId, @RequestParam String email);

    @PostMapping("/confirmLinkTg")
    void confirmLinkTg(@RequestBody LinkTgRequest request);
}
