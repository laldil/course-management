package kz.edu.astanait.notificationservice.controller;

import kz.edu.astanait.notificationservice.service.EmailService;
import kz.edu.astanait.notificationservice.service.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aldi
 * @since 06.06.2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final EmailService emailService;
    private final TelegramBot telegramBot;

    @PostMapping("/sendToken")
    public ResponseEntity<?> sendToken(@RequestParam String token, @RequestParam String email) {
        try {
            emailService.sendRegistrationToken(token, email);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sendTgMessage")
    public ResponseEntity<?> sendTgMessage(@RequestParam Long chatId, @RequestParam String message) {
        try {
            telegramBot.sendMessage(chatId, message);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
