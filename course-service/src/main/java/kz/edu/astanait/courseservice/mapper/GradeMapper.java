package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.GradeDto;
import kz.edu.astanait.courseservice.model.GradeEntity;
import kz.edu.astanait.courseservice.model.SubmissionEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author aldi
 * @since 25.05.2024
 */
@Mapper
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    @Mapping(target = "gradedDate", expression = "java(new java.util.Date())")
    @Mapping(target = "ownerId", expression = "java(submission.getUploadedById())")
    GradeEntity mapToEntity(GradeDto dto, @Context SubmissionEntity submission);
}
