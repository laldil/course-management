package kz.edu.astanait.courseservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 20.05.2024
 */

@Getter
@Setter
@Entity
@Table(name = "submission_box")
public class SubmissionBoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "due_date")
    private Date dueDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "submissionBox", orphanRemoval = true)
    private List<SubmissionEntity> submissions = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private ModuleEntity module;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }
}
