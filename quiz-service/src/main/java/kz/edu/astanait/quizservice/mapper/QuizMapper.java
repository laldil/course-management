package kz.edu.astanait.quizservice.mapper;

import kz.edu.astanait.quizservice.dto.CreateQuizRequest;
import kz.edu.astanait.quizservice.model.QuizEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    QuizEntity mapToEntity(CreateQuizRequest request);
}
