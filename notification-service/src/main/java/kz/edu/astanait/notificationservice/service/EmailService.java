package kz.edu.astanait.notificationservice.service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author aldi
 * @since 06.06.2024
 */
@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Async
    public void sendRegistrationToken(String token, String email) {
        String text = "Hello! \n" +
                "  \n" +
                "Your verification code: " + token + "\n";
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setSubject("Verification code");
            helper.setTo(email);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

