package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.CourseResponse;
import kz.edu.astanait.courseservice.dto.CreateCourseRequest;
import kz.edu.astanait.courseservice.dto.UpdateCourseRequest;
import kz.edu.astanait.courseservice.model.CourseEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author aldi
 * @since 10.02.2024
 */
@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity mapToEntity(CreateCourseRequest request);

    CourseResponse mapToResponseDto(CourseEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateCourseRequest request, @MappingTarget CourseEntity entity);
}
