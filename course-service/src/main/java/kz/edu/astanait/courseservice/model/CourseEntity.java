package kz.edu.astanait.courseservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 10.02.2024
 */

@Setter
@Getter
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "instructor_id")
    private Long instructorId;

    @ElementCollection
    @CollectionTable(name = "course_students_id",
            joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "students_id")
    private Set<Long> studentsId;

    @JsonManagedReference
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuleEntity> modules = new LinkedList<>();
}
