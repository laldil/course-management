package kz.edu.astanait.courseservice.controller;

import kz.edu.astanait.courseservice.dto.CreateCourseRequest;
import kz.edu.astanait.courseservice.service.CourseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author aldi
 * @since 10.02.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCourseRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAll() {
        try {
            return ResponseEntity.ok().body(courseService.list());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam List<Long> ids) {
        try {
            return ResponseEntity.ok().body(courseService.listById(ids));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(courseService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{courseId}/addStudents")
    public ResponseEntity<?> addStudents(@RequestParam Set<Long> studentIds, @PathVariable Long courseId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.addStudents(studentIds, courseId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
