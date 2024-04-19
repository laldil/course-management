package kz.edu.astanait.courseservice.dto;

import kz.edu.astanait.courseservice.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 31.03.2024
 */
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String name;
    private List<Long> courseIds;
}
