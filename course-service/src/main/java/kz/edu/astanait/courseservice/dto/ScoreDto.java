package kz.edu.astanait.courseservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author aldi
 * @since 02.06.2024
 */
@Data
public class ScoreDto {
    private Long id;
    private Long userId;
    private BigDecimal currentScore;
    private BigDecimal allTimeScore;
    private Date lastModified;
}
