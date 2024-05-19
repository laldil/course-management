package kz.edu.astanait.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author aldi
 * @since 16.05.2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadFileDto {
    private MultipartFile file;
}
