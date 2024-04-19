package kz.edu.astanait.authentiactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 01.04.2024
 */

@AllArgsConstructor
@Data
public class UserShortInfoDto {
    private Long id;
    private String name;
    private List<Long> courseIds;
}
