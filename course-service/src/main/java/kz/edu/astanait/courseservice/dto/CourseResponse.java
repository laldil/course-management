package kz.edu.astanait.courseservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 31.03.2024
 */
@Data
public class CourseResponse {
    private Long id;

    private String title;

    private String description;

    private Date createDate;

    private UserDto instructor;

    private List<UserDto> students;

    private List<ModuleDto> modules;
}
