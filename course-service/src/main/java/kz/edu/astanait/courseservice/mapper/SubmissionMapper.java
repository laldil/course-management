package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.client.FileClient;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.FileResponse;
import kz.edu.astanait.courseservice.dto.UserDto;
import kz.edu.astanait.courseservice.dto.api.ApiDataResponse;
import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionDto;
import kz.edu.astanait.courseservice.dto.submission.UpdateSubmissionRequest;
import kz.edu.astanait.courseservice.model.SubmissionEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author aldi
 * @since 21.05.2024
 */

@Mapper
public interface SubmissionMapper {
    SubmissionMapper INSTANCE = Mappers.getMapper(SubmissionMapper.class);

    @Mapping(target = "uploadDate", expression = "java(new java.util.Date())")
    SubmissionEntity mapToEntity(CreateSubmissionDto dto);

    @Mapping(target = "files", expression = "java(getFilesInfo(entity.getFileIds(), fileClient))")
    @Mapping(target = "uploadedBy", expression = "java(getUserInfo(entity.getUploadedById(), userClient))")
    SubmissionDto mapToDto(SubmissionEntity entity, @Context FileClient fileClient, @Context UserClient userClient);

    @Mapping(target = "uploadedById", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateSubmissionRequest request, @MappingTarget SubmissionEntity entity);

    default List<FileResponse> getFilesInfo(List<Long> fileIds, FileClient fileClient) {
        return fileIds.parallelStream().map(fileClient::getFileInfo).toList();
    }

    default UserDto getUserInfo(Long id, UserClient userClient) {
        ApiDataResponse<UserDto> response = userClient.findUserById(id);
        return response.getSuccess() ? response.getData() : new UserDto();
    }
}
