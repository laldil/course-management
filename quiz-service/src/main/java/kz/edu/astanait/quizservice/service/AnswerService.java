package kz.edu.astanait.quizservice.service;

import kz.edu.astanait.quizservice.dto.CreateAnswerRequest;
import kz.edu.astanait.quizservice.model.AnswerEntity;
import kz.edu.astanait.quizservice.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author aldi
 * @since 11.02.2024
 */
@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerEntity create(CreateAnswerRequest request) {
        AnswerEntity answer = new AnswerEntity();

        answer.setCreatorId(request.getCreatorId());
        answer.setCreateDate(new Date());
        answer.setText(request.getText());

        return answerRepository.save(answer);
    }
}
