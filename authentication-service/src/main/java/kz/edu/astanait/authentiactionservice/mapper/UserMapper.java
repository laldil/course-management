package kz.edu.astanait.authentiactionservice.mapper;

import kz.edu.astanait.authentiactionservice.dto.CreateUserResponse;
import kz.edu.astanait.authentiactionservice.dto.LoginResponse;
import kz.edu.astanait.authentiactionservice.dto.UserProfileDto;
import kz.edu.astanait.authentiactionservice.dto.UserShortInfoDto;
import kz.edu.astanait.authentiactionservice.model.RoleEntity;
import kz.edu.astanait.authentiactionservice.model.UserEntity;
import kz.edu.astanait.authentiactionservice.model.enums.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author aldi
 * @since 06.04.2024
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    CreateUserResponse mapToCreateResponse(UserEntity entity);

    UserShortInfoDto mapToShortInfo(UserEntity entity);

    @Mapping(target = "user", expression = "java(mapToProfile(entity))")
    LoginResponse mapToLoginResponse(UserEntity entity);

    @Mapping(target = "roles", expression = "java(getRoles(entity))")
    UserProfileDto mapToProfile(UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "roles", ignore = true)
    void updateFromDto(UserProfileDto dto, @MappingTarget UserEntity entity);

    default Set<Role> getRoles(UserEntity entity) {
        return entity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet());
    }
}
