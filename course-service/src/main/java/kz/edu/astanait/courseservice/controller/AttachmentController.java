package kz.edu.astanait.courseservice.controller;

import kz.edu.astanait.courseservice.dto.UpdateAttachmentRequest;
import kz.edu.astanait.courseservice.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aldi
 * @since 12.05.2024
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody List<UpdateAttachmentRequest> request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(attachmentService.update(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
