package kz.edu.astanait.courseservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author aldi
 * @since 25.05.2024
 */

@Setter
@Getter
@Entity
@Table(name = "grade")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private BigDecimal grade;

    @Column(name = "comment")
    private String comment;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "graded_by_id")
    private Long gradedById;

    @Column(name = "graded_date")
    private Date gradedDate;

    public void setGrade(BigDecimal grade) {
        if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        this.grade = grade;
    }
}
