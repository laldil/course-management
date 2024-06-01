package kz.edu.astanait.courseservice.dto;

import kz.edu.astanait.courseservice.dto.enums.ScoreTransactionType;
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
public class UpdateScoreRequest {
    private Long userId;
    private BigDecimal amount;
    private ScoreTransactionType transactionType;
}
