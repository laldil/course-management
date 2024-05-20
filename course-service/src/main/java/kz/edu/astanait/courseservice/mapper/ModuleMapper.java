package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.client.FileClient;
import kz.edu.astanait.courseservice.dto.FileResponse;
import kz.edu.astanait.courseservice.dto.ModuleDto;
import kz.edu.astanait.courseservice.dto.ModuleRequestDto;
import kz.edu.astanait.courseservice.model.ModuleEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author aldi
 * @since 21.04.2024
 */
@Mapper
public interface ModuleMapper {

    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    @Mapping(target = "attachments", ignore = true)
    ModuleEntity mapToEntity(ModuleRequestDto request);

    @Mapping(target = "files", expression = "java(getFilesInfo(entity.getFileIds(), fileClient))")
    ModuleDto mapToDto(ModuleEntity entity, @Context FileClient fileClient);

    default List<FileResponse> getFilesInfo(List<Long> fileIds, FileClient fileClient) {
        return fileIds.parallelStream().map(fileClient::getFileInfo).toList();
    }
}
