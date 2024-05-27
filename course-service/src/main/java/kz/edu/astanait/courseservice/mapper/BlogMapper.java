package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.BlogDto;
import kz.edu.astanait.courseservice.dto.BlogRequest;
import kz.edu.astanait.courseservice.model.BlogEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author aldi
 * @since 27.05.2024
 */

@Mapper
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Mapping(target = "author", ignore = true)
    BlogDto mapToDto(BlogEntity entity);

    @Mapping(target = "date", expression = "java(new java.util.Date())")
    BlogEntity mapToEntity(BlogRequest blogRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(BlogRequest request, @MappingTarget BlogEntity entity);
}
