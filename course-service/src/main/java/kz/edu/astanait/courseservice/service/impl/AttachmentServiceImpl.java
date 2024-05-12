package kz.edu.astanait.courseservice.service.impl;

import kz.edu.astanait.courseservice.dto.CreateAttachmentRequest;
import kz.edu.astanait.courseservice.dto.UpdateAttachmentRequest;
import kz.edu.astanait.courseservice.mapper.AttachmentMapper;
import kz.edu.astanait.courseservice.model.AttachmentEntity;
import kz.edu.astanait.courseservice.model.ModuleEntity;
import kz.edu.astanait.courseservice.repository.AttachmentRepository;
import kz.edu.astanait.courseservice.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aldi
 * @since 21.04.2024
 */

@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public AttachmentEntity create(CreateAttachmentRequest request, ModuleEntity module) {
        AttachmentEntity attachment = new AttachmentEntity();

        attachment.setAttachmentText(request.getAttachmentText());
        attachment.setAttachmentType(request.getAttachmentType());
        attachment.setModule(module);

        return attachmentRepository.save(attachment);
    }

    @Override
    public List<AttachmentEntity> update(List<UpdateAttachmentRequest> request) {
        List<AttachmentEntity> response = new ArrayList<>();

        request.forEach(updateRequest ->
                attachmentRepository.findById(updateRequest.getId()).ifPresent(attachment -> {
                    AttachmentMapper.INSTANCE.update(updateRequest, attachment);
                    AttachmentEntity savedAttachment = attachmentRepository.save(attachment);

                    response.add(savedAttachment);
                }));

        return response;
    }
}
