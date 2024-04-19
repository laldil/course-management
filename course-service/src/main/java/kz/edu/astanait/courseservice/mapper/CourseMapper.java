package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.CourseResponse;
import kz.edu.astanait.courseservice.dto.CreateCourseRequest;
import kz.edu.astanait.courseservice.model.CourseEntity;
import org.mapstruct.Mapper;
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
}
