package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.client.FileClient;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionBoxDto;
import kz.edu.astanait.courseservice.dto.submission.FullSubmissionBoxDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionBoxDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionDto;
import kz.edu.astanait.courseservice.model.SubmissionBoxEntity;
import kz.edu.astanait.courseservice.model.SubmissionEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author aldi
 * @since 21.05.2024
 */

@Mapper
public interface SubmissionBoxMapper {
    SubmissionBoxMapper INSTANCE = Mappers.getMapper(SubmissionBoxMapper.class);

    SubmissionBoxEntity mapToEntity(CreateSubmissionBoxDto dto);

    SubmissionBoxDto mapToDto(SubmissionBoxEntity entity);

    @Mapping(target = "submissions", expression = "java(getSubmissions(entity.getSubmissions(), userClient, fileClient))")
    FullSubmissionBoxDto mapToFullDto(SubmissionBoxEntity entity, @Context UserClient userClient, @Context FileClient fileClient);

    default List<SubmissionDto> getSubmissions(List<SubmissionEntity> submissions, UserClient userClient, FileClient fileClient) {
        if (submissions == null) {
            return null;
        }
        return submissions.stream().map(
                submission -> SubmissionMapper.INSTANCE.mapToDto(submission, fileClient, userClient)
        ).toList();
    }
}
