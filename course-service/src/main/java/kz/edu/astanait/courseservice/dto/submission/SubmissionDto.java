package kz.edu.astanait.courseservice.dto.submission;

import kz.edu.astanait.courseservice.dto.FileResponse;
import kz.edu.astanait.courseservice.dto.UserDto;
import kz.edu.astanait.courseservice.model.GradeEntity;
import kz.edu.astanait.courseservice.model.enums.SubmissionStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 21.05.2024
 */

@Data
public class SubmissionDto {
    private Long id;
    private UserDto uploadedBy;
    private String comment;
    private List<FileResponse> files;
    private Date uploadDate;
    private Date updateDate;
    private SubmissionStatus status;
    private GradeEntity grade;
}
