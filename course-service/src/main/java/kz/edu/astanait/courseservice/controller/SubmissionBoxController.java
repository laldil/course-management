package kz.edu.astanait.courseservice.controller;

import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionBoxDto;
import kz.edu.astanait.courseservice.service.SubmissionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 21.05.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course/submission-box")
public class SubmissionBoxController {
    private final SubmissionBoxService submissionBoxService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(submissionBoxService.get(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/short")
    public ResponseEntity<?> getShortInfo(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(submissionBoxService.getShortInfo(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateSubmissionBoxDto request, @RequestParam Long moduleId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(submissionBoxService.create(request, moduleId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok().body(submissionBoxService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
