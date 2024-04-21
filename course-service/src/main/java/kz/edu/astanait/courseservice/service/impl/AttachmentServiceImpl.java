package kz.edu.astanait.courseservice.service.impl;

import kz.edu.astanait.courseservice.dto.CreateAttachmentRequest;
import kz.edu.astanait.courseservice.model.AttachmentEntity;
import kz.edu.astanait.courseservice.repository.AttachmentRepository;
import kz.edu.astanait.courseservice.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author aldi
 * @since 21.04.2024
 */

@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public AttachmentEntity create(CreateAttachmentRequest request) {
        AttachmentEntity attachment = new AttachmentEntity();

        attachment.setAttachmentText(request.getAttachmentText());
        attachment.setAttachmentType(request.getAttachmentType());

        return attachmentRepository.save(attachment);
    }
}
