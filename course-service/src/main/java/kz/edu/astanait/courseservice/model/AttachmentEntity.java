package kz.edu.astanait.courseservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.edu.astanait.courseservice.model.enums.AttachmentType;
import lombok.Data;

/**
 * @author aldi
 * @since 16.04.2024
 */

@Data
@Entity
@Table(name = "attachment")
public class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "attachment_type")
    private AttachmentType attachmentType;

    @Column(name = "attachment_text")
    private String attachmentText;
}
