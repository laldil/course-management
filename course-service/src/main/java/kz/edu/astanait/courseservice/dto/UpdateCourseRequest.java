package kz.edu.astanait.courseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author aldi
 * @since 20.04.2024
 */

@Data
public class UpdateCourseRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("instructor_id")
    private Long instructorId;
}
