package kz.edu.astanait.quizservice.controller;

import kz.edu.astanait.quizservice.dto.CreateQuestionRequest;
import kz.edu.astanait.quizservice.model.QuestionEntity;
import kz.edu.astanait.quizservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 11.02.2024
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateQuestionRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
