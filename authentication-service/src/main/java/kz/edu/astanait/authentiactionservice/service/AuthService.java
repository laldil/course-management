package kz.edu.astanait.authentiactionservice.service;

import kz.edu.astanait.authentiactionservice.dto.LoginRequest;
import kz.edu.astanait.authentiactionservice.dto.LoginResponse;
import kz.edu.astanait.authentiactionservice.dto.UserProfileDto;
import kz.edu.astanait.authentiactionservice.mapper.UserMapper;
import kz.edu.astanait.authentiactionservice.model.RoleEntity;
import kz.edu.astanait.authentiactionservice.model.UserEntity;
import kz.edu.astanait.authentiactionservice.repository.UserRepository;
import kz.edu.astanait.authentiactionservice.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author aldi
 * @since 19.03.2024
 */
@Service
public class AuthService {

    private final String AUTH_TOKEN_TYPE = "Bearer ";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email or password is not correct");
        }
        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException("User not found"));
        String accessToken = jwtTokenUtils.createToken(user);

        LoginResponse response = UserMapper.INSTANCE.mapToLoginResponse(user);
        response.setTokenType(AUTH_TOKEN_TYPE);
        response.setAccessToken(accessToken);
        return response;
    }

    public Boolean validateToken(String token) {
        return jwtTokenUtils.validateToken(token);
    }
}
