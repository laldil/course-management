package kz.edu.astanait.courseservice.client;

import kz.edu.astanait.courseservice.dto.FileResponse;
import kz.edu.astanait.courseservice.dto.UploadFileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author aldi
 * @since 16.05.2024
 */

@FeignClient(name = "file-service", url = "${application.config.url.file-service}")
public interface FileClient {
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    FileResponse uploadFile(@ModelAttribute UploadFileDto dto, @RequestParam Long userId);

    @GetMapping("/{id}")
    FileResponse getFileInfo(@PathVariable Long id);

    @DeleteMapping("/{id}")
    Boolean deleteFile(@PathVariable Long id);

}
