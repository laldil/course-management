package kz.edu.astanait.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 10.04.2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FullUserDto {
    private Long id;
    private String name;
    private String email;
    private Set<String> roles;
    private List<Long> courseIds;

    public FullUserDto(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
