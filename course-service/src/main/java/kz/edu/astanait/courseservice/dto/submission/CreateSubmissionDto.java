package kz.edu.astanait.courseservice.dto.submission;

import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 21.05.2024
 */
@Data
public class CreateSubmissionDto {
    private Long uploadedById;
    private List<Long> fileIds;
}
