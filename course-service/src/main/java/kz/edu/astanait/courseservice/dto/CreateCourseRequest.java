package kz.edu.astanait.courseservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * @author aldi
 * @since 10.02.2024
 */
@Getter
@AllArgsConstructor
public class CreateCourseRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("students_id")
    private Set<Long> studentsId;
}
