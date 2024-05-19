package kz.edu.astanait.fileservice.service;

import kz.edu.astanait.fileservice.dto.UploadFileDto;
import kz.edu.astanait.fileservice.entity.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aldi
 * @since 26.04.2024
 */
public interface FileService {
    FileEntity upload(MultipartFile file, Long userId);

    List<FileEntity> uploadList(List<MultipartFile> files, Long userId);

    Pair<String, Resource> getFile(Long id);

    FileEntity getInfo(Long id);
}
