package kz.edu.astanait.courseservice.controller;

import kz.edu.astanait.courseservice.dto.GradeDto;
import kz.edu.astanait.courseservice.dto.submission.CreateSubmissionDto;
import kz.edu.astanait.courseservice.dto.submission.UpdateSubmissionRequest;
import kz.edu.astanait.courseservice.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/api/course/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    // todo (temp solution) get userId from jwt token
    @GetMapping
    public ResponseEntity<?> get(@RequestParam Long submissionBoxId, @RequestParam Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(submissionService.get(submissionBoxId, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateSubmissionDto request, @RequestParam Long submissionBoxId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(submissionService.create(request, submissionBoxId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{submissionBoxId}")
    public ResponseEntity<?> update(@RequestBody UpdateSubmissionRequest request, @PathVariable Long submissionBoxId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(submissionService.update(request, submissionBoxId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<?> setGrade(@PathVariable Long id, @RequestBody GradeDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(submissionService.setGrade(id, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok().body(submissionService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
