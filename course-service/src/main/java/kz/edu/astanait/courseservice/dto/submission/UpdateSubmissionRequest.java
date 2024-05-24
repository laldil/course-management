package kz.edu.astanait.courseservice.dto.submission;

import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 24.05.2024
 */

@Data
public class UpdateSubmissionRequest {
    private Long uploadedById;
    private String comment;
    private List<Long> fileIds;

}
