package kz.edu.astanait.scoreservice.controller;

import kz.edu.astanait.scoreservice.dto.UpdateScoreRequest;
import kz.edu.astanait.scoreservice.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aldi
 * @since 01.06.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(scoreService.getByUser(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<?> create(@PathVariable Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(scoreService.create(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam List<Long> userIds) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(scoreService.getByUserList(userIds));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateScoreRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(scoreService.update(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTop() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(scoreService.getScoreTop());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
