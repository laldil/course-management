package kz.edu.astanait.scoreservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.edu.astanait.scoreservice.enums.ScoreTransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author aldi
 * @since 01.06.2024
 */

@Getter
@Setter
@Entity
@Table(name = "score_log")
public class ScoreLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "score_id")
    private ScoreEntity score;

    @Column(name = "created_date")
    private Date createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private ScoreTransactionType transactionType;

    @Column(name = "amount")
    private BigDecimal amount;
}
