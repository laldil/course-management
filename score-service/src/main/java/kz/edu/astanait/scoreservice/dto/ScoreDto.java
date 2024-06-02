package kz.edu.astanait.scoreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author aldi
 * @since 02.06.2024
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScoreDto {
    private UserDto user;
    private BigDecimal allTimeScore;
}
