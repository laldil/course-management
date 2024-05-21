package kz.edu.astanait.courseservice.dto.submission;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author aldi
 * @since 21.05.2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class FullSubmissionBoxDto extends SubmissionBoxDto {
    List<SubmissionDto> submissions;
}
