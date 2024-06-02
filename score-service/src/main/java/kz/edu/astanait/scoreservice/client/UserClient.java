package kz.edu.astanait.scoreservice.client;

import kz.edu.astanait.scoreservice.dto.ApiListResponse;
import kz.edu.astanait.scoreservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author aldi
 * @since 02.06.2024
 */

@FeignClient(name = "authentication-service", url = "${application.config.url.auth-service}")
public interface UserClient {
    @GetMapping("/users-list")
    ApiListResponse<UserDto> findUsersByIdIn(@RequestParam("ids") List<Long> ids);

}
