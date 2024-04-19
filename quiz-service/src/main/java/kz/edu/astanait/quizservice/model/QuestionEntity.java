package kz.edu.astanait.quizservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToMany
    @JoinTable(name = "question_answers",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "id"))
    private List<AnswerEntity> answers;

    @OneToOne
    private AnswerEntity correctAnswer;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "created_date")
    private Date createDate;
}
