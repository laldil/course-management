package kz.edu.astanait.quizservice.controller;

import kz.edu.astanait.quizservice.dto.CreateQuizRequest;
import kz.edu.astanait.quizservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateQuizRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(quizService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "ids") List<Long> quizIds) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(quizService.list(quizIds));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
