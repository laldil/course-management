package kz.edu.astanait.quizservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author aldi
 * @since 11.02.2024
 */
@Data
public class CreateQuizRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("questions_id")
    private List<Long> questionsId;

    @JsonProperty("creator_id")
    private Long creatorId;
}
