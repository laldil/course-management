package kz.edu.astanait.authentiactionservice.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author aldi
 * @since 02.06.2024
 */
@Data
public class UpdateScoreRequest {
    private Long userId;
    private BigDecimal amount;
    private ScoreTransactionType transactionType;
}
