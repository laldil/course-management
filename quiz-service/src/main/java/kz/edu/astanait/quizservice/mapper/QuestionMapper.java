package kz.edu.astanait.quizservice.mapper;

import kz.edu.astanait.quizservice.dto.CreateQuestionRequest;
import kz.edu.astanait.quizservice.model.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author aldi
 * @since 11.02.2024
 */
@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionEntity mapToEntity(CreateQuestionRequest request);
}
