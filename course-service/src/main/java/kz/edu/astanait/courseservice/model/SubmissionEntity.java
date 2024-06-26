package kz.edu.astanait.courseservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import kz.edu.astanait.courseservice.model.enums.SubmissionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 20.05.2024
 */

@Getter
@Setter
@Entity
@Table(name = "submission")
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "uploaded_by_id")
    private Long uploadedById;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "submission_files", joinColumns = @JoinColumn(name = "submission_id"))
    @Column(name = "file_id")
    private List<Long> fileIds;

    @Column(name = "upload_date")
    private Date uploadDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private GradeEntity grade;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submission_box_id")
    private SubmissionBoxEntity submissionBox;

    @Column(name = "scored")
    private Boolean scored = false;

    @PrePersist
    protected void onCreate() {
        updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }
}
