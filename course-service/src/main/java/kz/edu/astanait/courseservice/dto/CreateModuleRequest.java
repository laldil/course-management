package kz.edu.astanait.courseservice.dto;

import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 21.04.2024
 */

@Data
public class CreateModuleRequest {
    private String title;
    private List<CreateAttachmentRequest> attachments;
}
