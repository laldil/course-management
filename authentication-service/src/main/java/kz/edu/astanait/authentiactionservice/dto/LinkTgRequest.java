package kz.edu.astanait.authentiactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aldi
 * @since 06.06.2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkTgRequest {
    private Long tgId;
    private String code;
    private String tgUserName;
    private String tgFirstName;
    private String tgLastName;
}