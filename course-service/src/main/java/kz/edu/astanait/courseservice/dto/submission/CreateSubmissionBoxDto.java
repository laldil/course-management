package kz.edu.astanait.courseservice.dto.submission;

import lombok.Data;

import java.util.Date;

/**
 * @author aldi
 * @since 21.05.2024
 */
@Data
public class CreateSubmissionBoxDto {
    private String title;
    private String description;
    private Date dueDate;
    private Long createdById;
}
