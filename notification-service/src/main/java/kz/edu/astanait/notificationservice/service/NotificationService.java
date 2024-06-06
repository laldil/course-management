package kz.edu.astanait.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author aldi
 * @since 06.06.2024
 */

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final EmailService emailService;


}
