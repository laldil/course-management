package kz.edu.astanait.courseservice.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author aldi
 * @since 27.05.2024
 */

@Data
public class BlogDto {
    private Long id;
    private String title;
    private String author;
    private Date date;
    private String summary;
}
