package kz.edu.astanait.quizservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author aldi
 * @since 11.02.2024
 */

@Data
public class CreateAnswerRequest {
    @JsonProperty("text")
    private String text;

    @JsonProperty("creator_id")
    private Long creatorId;
}
