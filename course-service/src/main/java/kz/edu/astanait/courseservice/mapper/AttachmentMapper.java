package kz.edu.astanait.courseservice.mapper;

import kz.edu.astanait.courseservice.dto.UpdateAttachmentRequest;
import kz.edu.astanait.courseservice.model.AttachmentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author aldi
 * @since 12.05.2024
 */
@Mapper
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateAttachmentRequest request, @MappingTarget AttachmentEntity entity);

}
