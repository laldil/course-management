package kz.edu.astanait.fileservice.controller;

import com.google.common.net.HttpHeaders;
import kz.edu.astanait.fileservice.dto.UploadFileDto;
import kz.edu.astanait.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 01.05.2024
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@ModelAttribute UploadFileDto dto, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok().body(fileService.upload(dto.getFile(), userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        try {
            var file = fileService.getFile(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFirst() + "\"")
                    .body(file.getSecond());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
