package kz.edu.astanait.courseservice.controller;

import kz.edu.astanait.courseservice.dto.BlogRequest;
import kz.edu.astanait.courseservice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 27.05.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(blogService.getList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/blog/{blogId}")
    public ResponseEntity<?> update(@PathVariable Long blogId, @RequestBody BlogRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(blogService.update(blogId, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/blog/create")
    public ResponseEntity<?> create(@RequestBody BlogRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(blogService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(blogService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
