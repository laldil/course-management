package kz.edu.astanait.fileservice.service;

import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author aldi
 * @since 26.04.2024
 */
public interface FileService {
    Boolean upload(MultipartFile file, Long userId);

    Pair<String, Resource> getFile(Long id);
}
