package kz.edu.astanait.fileservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author aldi
 * @since 01.05.2024
 */

@Data
public class UploadFileDto {
    private MultipartFile file;
}
