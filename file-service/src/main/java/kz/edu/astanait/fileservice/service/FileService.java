package kz.edu.astanait.fileservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author aldi
 * @since 26.04.2024
 */
public interface FileService {
    Boolean upload(MultipartFile file);

    MultipartFile getFile();
}
