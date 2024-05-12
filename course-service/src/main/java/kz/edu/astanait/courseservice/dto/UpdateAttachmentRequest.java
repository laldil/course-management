package kz.edu.astanait.courseservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aldi
 * @since 12.05.2024
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateAttachmentRequest extends CreateAttachmentRequest{
    private Long id;
}
