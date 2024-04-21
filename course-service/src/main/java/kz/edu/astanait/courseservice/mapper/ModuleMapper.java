package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.CreateModuleRequest;
import kz.edu.astanait.courseservice.model.AttachmentEntity;
import kz.edu.astanait.courseservice.model.ModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

/**
 * @author aldi
 * @since 21.04.2024
 */
@Mapper
public interface ModuleMapper {

    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    @Mapping(target = "attachments", ignore = true)
    ModuleEntity mapToEntity(CreateModuleRequest request);

    default List<AttachmentEntity> mapToAttachmentEntity(CreateModuleRequest request) {
        return request.getAttachments() == null || request.getAttachments().isEmpty()
                ? Collections.emptyList()
                : request.getAttachments().stream().map(attachment -> AttachmentEntity
                .builder()
                .attachmentType(attachment.getAttachmentType())
                .attachmentText(attachment.getAttachmentText())
                .build()
        ).toList();
    }
}
