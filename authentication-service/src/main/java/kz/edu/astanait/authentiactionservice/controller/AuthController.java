package kz.edu.astanait.authentiactionservice.controller;

import kz.edu.astanait.authentiactionservice.controller.api.ApiDataResponse;
import kz.edu.astanait.authentiactionservice.dto.CreateUserRequest;
import kz.edu.astanait.authentiactionservice.dto.CreateUserResponse;
import kz.edu.astanait.authentiactionservice.dto.LoginRequest;
import kz.edu.astanait.authentiactionservice.dto.LoginResponse;
import kz.edu.astanait.authentiactionservice.service.AuthService;
import kz.edu.astanait.authentiactionservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 23.03.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiDataResponse<CreateUserResponse>> createUser(@RequestBody CreateUserRequest request) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(userService.createUser(request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiDataResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(ApiDataResponse.create(authService.login(request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiDataResponse<Boolean>> validateToken(@RequestParam String token) {
        try {
            return ResponseEntity.ok().body(ApiDataResponse.create(authService.validateToken(token)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiDataResponse.failed(e.getMessage()));
        }
    }
}
