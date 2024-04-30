package kz.edu.astanait.fileservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author aldi
 * @since 01.05.2024
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "upload_date")
    private Date uploadDate;

    @Column(name = "uploaded_by_id")
    private Long uploadedById;
}
