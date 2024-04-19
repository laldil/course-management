package kz.edu.astanait.quizservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 11.02.2024
 */

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private List<QuestionEntity> questions;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "creator_id")
    private Long creatorId;
}
