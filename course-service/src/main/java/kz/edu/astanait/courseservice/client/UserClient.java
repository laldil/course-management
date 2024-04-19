package kz.edu.astanait.courseservice.client;

import kz.edu.astanait.courseservice.dto.FullUserDto;
import kz.edu.astanait.courseservice.dto.UserDto;
import kz.edu.astanait.courseservice.dto.api.ApiDataResponse;
import kz.edu.astanait.courseservice.dto.api.ApiListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author aldi
 * @since 31.03.2024
 */

@FeignClient(name = "authentication-service", url = "${application.config.url.auth-service}")
public interface UserClient {
    @GetMapping("/users-list")
    ApiListResponse<UserDto> findUsersByIdIn(@RequestParam("ids") List<Long> ids);

    @GetMapping("/user")
    ApiDataResponse<UserDto> findUserById(@RequestParam Long id);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
    ApiDataResponse<FullUserDto> updateById(@PathVariable Long id, FullUserDto userDto);

}
