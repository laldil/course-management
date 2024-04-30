package kz.edu.astanait.fileservice.repository;

import kz.edu.astanait.fileservice.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aldi
 * @since 01.05.2024
 */
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
