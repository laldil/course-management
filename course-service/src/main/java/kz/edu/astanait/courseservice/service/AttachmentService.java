package kz.edu.astanait.courseservice.service;

import kz.edu.astanait.courseservice.dto.CreateAttachmentRequest;
import kz.edu.astanait.courseservice.model.AttachmentEntity;

/**
 * @author aldi
 * @since 21.04.2024
 */
public interface AttachmentService {
    AttachmentEntity create(CreateAttachmentRequest request);
}
