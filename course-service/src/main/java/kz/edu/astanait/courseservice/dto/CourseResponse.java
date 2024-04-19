package kz.edu.astanait.courseservice.dto;

import kz.edu.astanait.courseservice.model.ModuleEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    private List<ModuleEntity> modules;
}
