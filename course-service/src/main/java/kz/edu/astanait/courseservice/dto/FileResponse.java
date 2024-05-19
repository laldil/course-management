package kz.edu.astanait.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author aldi
 * @since 16.05.2024
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileResponse {
    private Long id;
    private String name;
    private String originalName;
    private Date uploadDate;
    private Long uploadedById;
}
