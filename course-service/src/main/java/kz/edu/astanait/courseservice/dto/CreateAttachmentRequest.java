package kz.edu.astanait.courseservice.dto;

import kz.edu.astanait.courseservice.model.enums.AttachmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author aldi
 * @since 21.04.2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttachmentRequest {
    private AttachmentType attachmentType;
    private String attachmentText;
}
