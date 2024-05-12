package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.ModuleRequestDto;
import kz.edu.astanait.courseservice.model.ModuleEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author aldi
 * @since 21.04.2024
 */
@Mapper
public interface ModuleMapper {

    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    @Mapping(target = "attachments", ignore = true)
    ModuleEntity mapToEntity(ModuleRequestDto request);

}
