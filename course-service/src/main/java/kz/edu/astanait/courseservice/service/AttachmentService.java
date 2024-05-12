package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.dto.CreateAttachmentRequest;
import kz.edu.astanait.courseservice.dto.UpdateAttachmentRequest;
import kz.edu.astanait.courseservice.model.AttachmentEntity;
import kz.edu.astanait.courseservice.model.ModuleEntity;

import java.util.List;

/**
 * @author aldi
 * @since 21.04.2024
 */
public interface AttachmentService {
    AttachmentEntity create(CreateAttachmentRequest request, ModuleEntity module);

    List<AttachmentEntity> update(List<UpdateAttachmentRequest> request);
}
