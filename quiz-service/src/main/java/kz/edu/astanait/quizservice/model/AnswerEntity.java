package kz.edu.astanait.quizservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author aldi
 * @since 11.02.2024
 */
@Getter
@Setter
@Entity
@Table(name = "answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "creator_id")
    private Long creatorId;
}
