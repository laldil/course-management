package kz.edu.astanait.courseservice.dto;

import kz.edu.astanait.courseservice.model.AttachmentEntity;
import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 20.05.2024
 */
@Data
public class ModuleDto {
    private Long id;
    private String title;
    private List<AttachmentEntity> attachments;
    private List<FileResponse> files;
}
