package kz.edu.astanait.fileservice.service.impl;

import kz.edu.astanait.fileservice.entity.FileEntity;
import kz.edu.astanait.fileservice.repository.FileRepository;
import kz.edu.astanait.fileservice.service.FileService;
import kz.edu.astanait.fileservice.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 26.04.2024
 */

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final MinioServiceImpl minioService;
    private final FileRepository fileRepository;

    @Override
    public FileEntity upload(MultipartFile file, Long userId) {
        if (file == null || file.getOriginalFilename() == null) {
            throw new RuntimeException("File upload failed");
        }

        var filename = FileUtils.generateFilename(file);
        try (InputStream inputStream = file.getInputStream()) {
            minioService.saveFile(inputStream, filename);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        var fileEntity = FileEntity.builder()
                .name(filename)
                .originalName(file.getOriginalFilename())
                .uploadedById(userId)
                .uploadDate(new Date())
                .build();
        return fileRepository.save(fileEntity);
    }

    @Override
    public List<FileEntity> uploadList(List<MultipartFile> files, Long userId) {
        return files.stream().map(file -> upload(file, userId)).toList();
    }

    @Override
    public Pair<String, Resource> getFile(Long id) {
        var fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("File with id %d not found", id)));

        return Pair.of(fileEntity.getOriginalName(), new InputStreamResource(minioService.getFileStream(fileEntity.getName())));
    }

    @Override
    public FileEntity getInfo(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }
}
