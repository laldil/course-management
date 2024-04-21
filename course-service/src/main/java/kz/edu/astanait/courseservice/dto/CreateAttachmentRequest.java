package kz.edu.astanait.courseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.edu.astanait.courseservice.model.enums.AttachmentType;
import lombok.Data;

/**
 * @author aldi
 * @since 21.04.2024
 */
@Data
public class CreateAttachmentRequest {
    @JsonProperty("attachment_type")
    private AttachmentType attachmentType;

    @JsonProperty("attachment_text")
    private String attachmentText;
}
