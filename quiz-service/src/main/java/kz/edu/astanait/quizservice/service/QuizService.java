package kz.edu.astanait.quizservice.service;

import kz.edu.astanait.quizservice.dto.CreateQuizRequest;
import kz.edu.astanait.quizservice.mapper.QuizMapper;
import kz.edu.astanait.quizservice.model.QuestionEntity;
import kz.edu.astanait.quizservice.model.QuizEntity;
import kz.edu.astanait.quizservice.repository.QuestionRepository;
import kz.edu.astanait.quizservice.repository.QuizRepository;
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
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizEntity create(CreateQuizRequest request) {
        QuizEntity quiz = QuizMapper.INSTANCE.mapToEntity(request);
        List<QuestionEntity> questions = questionRepository.findByIdIn(request.getQuestionsId());
        if (questions.isEmpty()) {
            throw new RuntimeException("Questions not found");
        }

        quiz.setCreatedDate(new Date());
        quiz.setQuestions(questions);

        return quizRepository.save(quiz);
    }

    public List<QuizEntity> list(List<Long> ids) {
        List<QuizEntity> quizList = quizRepository.findByIdIn(ids);
        if (quizList.isEmpty()) throw new RuntimeException("Quiz not found");
        return quizList;
    }
}
