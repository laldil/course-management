package kz.edu.astanait.courseservice.dto;

import lombok.Data;

/**
 * @author aldi
 * @since 27.05.2024
 */

@Data
public class BlogRequest {
    private String title;
    private String summary;
    private Long authorId;
}
