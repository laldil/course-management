package kz.edu.astanait.courseservice.controller;

import kz.edu.astanait.courseservice.dto.ModuleRequestDto;
import kz.edu.astanait.courseservice.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 21.04.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course/module")
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ModuleRequestDto request, @RequestParam Long courseId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.create(request, courseId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id, @RequestParam Long courseId) {
        try {
            return ResponseEntity.ok().body(moduleService.delete(id, courseId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
