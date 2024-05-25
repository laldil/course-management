package kz.edu.astanait.courseservice.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author aldi
 * @since 25.05.2024
 */
@Data
public class GradeDto {
    private BigDecimal grade;
    private String comment;
    // todo temp decision!!!
    private Long gradedById;
}
