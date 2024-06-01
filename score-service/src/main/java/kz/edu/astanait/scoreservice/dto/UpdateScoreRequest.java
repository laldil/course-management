package kz.edu.astanait.scoreservice.dto;

import kz.edu.astanait.scoreservice.enums.ScoreTransactionType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author aldi
 * @since 01.06.2024
 */

@Data
public class UpdateScoreRequest {
    private Long userId;
    private BigDecimal amount;
    private ScoreTransactionType transactionType;
}
