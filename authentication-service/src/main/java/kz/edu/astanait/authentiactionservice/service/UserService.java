package kz.edu.astanait.authentiactionservice.service;

import kz.edu.astanait.authentiactionservice.client.ScoreClient;
import kz.edu.astanait.authentiactionservice.dto.CreateUserRequest;
import kz.edu.astanait.authentiactionservice.dto.CreateUserResponse;
import kz.edu.astanait.authentiactionservice.dto.UpdateUserRequest;
import kz.edu.astanait.authentiactionservice.dto.UserProfileDto;
import kz.edu.astanait.authentiactionservice.dto.UserShortInfoDto;
import kz.edu.astanait.authentiactionservice.mapper.UserMapper;
import kz.edu.astanait.authentiactionservice.model.RoleEntity;
import kz.edu.astanait.authentiactionservice.model.UserEntity;
import kz.edu.astanait.authentiactionservice.model.enums.Role;
import kz.edu.astanait.authentiactionservice.repository.RoleRepository;
import kz.edu.astanait.authentiactionservice.repository.UserRepository;
import kz.edu.astanait.authentiactionservice.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author aldi
 * @since 24.03.2024
 */

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ScoreClient scoreClient;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ScoreClient scoreClient, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.scoreClient = scoreClient;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        validateCreateUserRequest(request);

        List<RoleEntity> roles = roleRepository.findByRoleIn(List.of(request.getRole()));
        UserEntity user = new UserEntity();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.copyOf(roles));

        UserEntity savedUser = userRepository.save(user);
        scoreClient.create(savedUser.getId());

        return UserMapper.INSTANCE.mapToCreateResponse(savedUser);
    }

    public UserProfileDto getProfile() {
        var user = userRepository.findById(SecurityUtils.getCurrentId())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        var profile = UserMapper.INSTANCE.mapToProfile(user);

        try {
            var score = scoreClient.getByUserId(user.getId());
            profile.setCurrentScore(score.getCurrentScore());
            profile.setAllTimeScore(score.getAllTimeScore());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return profile;
    }

    public List<UserShortInfoDto> getUserList(List<Long> ids) {
        List<UserEntity> users = userRepository.findByIdIn(ids);

        return users.stream().map(UserMapper.INSTANCE::mapToShortInfo).toList();
    }

    public UserShortInfoDto getById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        return UserMapper.INSTANCE.mapToShortInfo(user);
    }

    private void validateCreateUserRequest(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadCredentialsException("User with already exists");
        }
    }

    public List<UserProfileDto> getAll(List<Role> roles) {
        if (Objects.isNull(roles) || roles.isEmpty()) {
            return userRepository.findAll().stream().map(UserMapper.INSTANCE::mapToProfile).toList();
        } else {
            List<String> roleNames = roles.stream().map(Enum::name).toList();
            return userRepository.findByRoleNames(roleNames).stream()
                    .map(UserMapper.INSTANCE::mapToProfile)
                    .toList();
        }
    }

    public UserProfileDto updateById(Long id, UpdateUserRequest request) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new BadCredentialsException("User not found"));
        UserMapper.INSTANCE.updateFromDto(request, userEntity);

        if (request.getRoles() != null) {
            List<RoleEntity> roles = roleRepository.findByRoleIn(request.getRoles().stream().toList());
            userEntity.setRoles(new HashSet<>(roles));
        }

        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.INSTANCE.mapToProfile(savedUser);
    }

    public Boolean delete(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException("User not found"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).get();
        return new User(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
