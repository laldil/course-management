package kz.edu.astanait.quizservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 11.02.2024
 */

@Data
public class CreateQuestionRequest {
    @JsonProperty("text")
    private String text;

    @JsonProperty("answers_ids")
    private List<Long> answersIds;

    @JsonProperty("correct_answer_id")
    private Long correctAnswerId;

    @JsonProperty("creator_id")
    private Long creatorId;
}
