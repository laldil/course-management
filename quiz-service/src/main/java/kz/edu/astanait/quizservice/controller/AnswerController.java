package kz.edu.astanait.quizservice.controller;

import kz.edu.astanait.quizservice.dto.CreateAnswerRequest;
import kz.edu.astanait.quizservice.service.AnswerService;
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
@RequestMapping("/api/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateAnswerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.create(request));
    }
}
