package kz.edu.astanait.authentiactionservice.service;

import kz.edu.astanait.authentiactionservice.client.NotificationClient;
import kz.edu.astanait.authentiactionservice.dto.LinkTgRequest;
import kz.edu.astanait.authentiactionservice.dto.LoginRequest;
import kz.edu.astanait.authentiactionservice.dto.LoginResponse;
import kz.edu.astanait.authentiactionservice.mapper.UserMapper;
import kz.edu.astanait.authentiactionservice.model.UserEntity;
import kz.edu.astanait.authentiactionservice.model.VerificationTokenEntity;
import kz.edu.astanait.authentiactionservice.model.enums.VerificationType;
import kz.edu.astanait.authentiactionservice.repository.UserRepository;
import kz.edu.astanait.authentiactionservice.repository.VerificationTokenRepository;
import kz.edu.astanait.authentiactionservice.utils.JwtTokenUtils;
import kz.edu.astanait.authentiactionservice.utils.VerificationTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author aldi
 * @since 19.03.2024
 */
@Service
public class AuthService {

    private final String AUTH_TOKEN_TYPE = "Bearer ";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final NotificationClient notificationClient;

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, NotificationClient notificationClient, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.notificationClient = notificationClient;
        this.verificationTokenRepository = verificationTokenRepository;
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

    public void linkTgToUser(Long chatId, String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("User not found");
        }

        String token = VerificationTokenGenerator.getRegistrationCode();

        VerificationTokenEntity verificationTokenEntity = VerificationTokenEntity.builder()
                .email(email)
                .token(token)
                .createdDate(new Date())
                .verificationType(VerificationType.LINK_TG)
                .tgId(chatId).build();
        verificationTokenRepository.save(verificationTokenEntity);

        notificationClient.sendToken(token, verificationTokenEntity.getEmail());
    }

    public void confirmLinkTg(LinkTgRequest request) {
        VerificationTokenEntity token = verificationTokenRepository
                .findByTgIdAndTokenAndVerificationType(request.getTgId(), request.getCode(), VerificationType.LINK_TG)
                .orElseThrow(() -> new BadCredentialsException("The verification code is NOT correct"));

        UserEntity user = userRepository.findByEmail(token.getEmail())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        user.setTgId(request.getTgId());
        user.setTgAuthDate(new Date());
        user.setTgUserName(request.getTgUserName());
        user.setTgFirstName(request.getTgFirstName());
        user.setTgLastName(request.getTgLastName());
        userRepository.save(user);
    }

    public Boolean validateToken(String token) {
        return jwtTokenUtils.validateToken(token);
    }
}
