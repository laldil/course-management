package kz.edu.astanait.quizservice.service;

import kz.edu.astanait.quizservice.dto.CreateQuestionRequest;
import kz.edu.astanait.quizservice.mapper.QuestionMapper;
import kz.edu.astanait.quizservice.model.AnswerEntity;
import kz.edu.astanait.quizservice.model.QuestionEntity;
import kz.edu.astanait.quizservice.repository.AnswerRepository;
import kz.edu.astanait.quizservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author aldi
 * @since 11.02.2024
 */
@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionEntity create(CreateQuestionRequest request) {
        QuestionEntity question = QuestionMapper.INSTANCE.mapToEntity(request);

        AnswerEntity correctAnswer = answerRepository.findById(request.getCorrectAnswerId())
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        List<AnswerEntity> answers = answerRepository.findByIdIn(request.getAnswersIds());
        if (answers.isEmpty()) {
            throw new RuntimeException("Answers not found");
        }

        question.setCorrectAnswer(correctAnswer);
        question.setAnswers(answers);
        question.setCreateDate(new Date());

        return questionRepository.save(question);
    }
}
