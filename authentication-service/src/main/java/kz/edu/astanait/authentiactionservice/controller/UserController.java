package kz.edu.astanait.authentiactionservice.controller;

import kz.edu.astanait.authentiactionservice.controller.api.ApiDataResponse;
import kz.edu.astanait.authentiactionservice.controller.api.ApiListResponse;
import kz.edu.astanait.authentiactionservice.dto.UpdateUserRequest;
import kz.edu.astanait.authentiactionservice.dto.UserProfileDto;
import kz.edu.astanait.authentiactionservice.dto.UserShortInfoDto;
import kz.edu.astanait.authentiactionservice.model.enums.Role;
import kz.edu.astanait.authentiactionservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aldi
 * @since 25.03.2024
 */

// todo
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<ApiDataResponse<UserProfileDto>> getProfile() {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(userService.getProfile()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }

    @GetMapping("/users-list")
    public ResponseEntity<ApiListResponse<UserShortInfoDto>> getList(@RequestParam List<Long> ids) {
        try {
            return ResponseEntity.ok().body(ApiListResponse.create(userService.getUserList(ids)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiListResponse.failed(e.getMessage()));
        }
    }

    // todo сделать всё в один рест через criteriaBuilder
    @GetMapping("/users-all")
    public ResponseEntity<ApiListResponse<UserProfileDto>> getAll(@RequestParam(required = false) List<Role> roles) {
        try {
            return ResponseEntity.ok().body(ApiListResponse.create(userService.getAll(roles)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiListResponse.failed(e.getMessage()));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<ApiDataResponse<UserShortInfoDto>> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(userService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<ApiDataResponse<UserProfileDto>> updateById(@PathVariable Long id, @RequestBody UpdateUserRequest user) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(userService.updateById(id, user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/user")
    public ResponseEntity<ApiDataResponse<Boolean>> delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(userService.delete(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }
}