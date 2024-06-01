package kz.edu.astanait.scoreservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author aldi
 * @since 01.06.2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score")
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "current_score")
    private BigDecimal currentScore;

    @Column(name = "all_time_score")
    private BigDecimal allTimeScore;

    @Column(name = "last_modified")
    private Date lastModified;

    public ScoreEntity(Long userId) {
        this.userId = userId;
        this.currentScore = BigDecimal.ZERO;
        this.allTimeScore = BigDecimal.ZERO;
        this.lastModified = new Date();
    }
}
