package kz.edu.astanait.courseservice.aspect;

import kz.edu.astanait.courseservice.client.NotificationClient;
import kz.edu.astanait.courseservice.client.UserClient;
import kz.edu.astanait.courseservice.dto.GradeDto;
import kz.edu.astanait.courseservice.dto.submission.SubmissionDto;
import kz.edu.astanait.courseservice.model.CourseEntity;
import kz.edu.astanait.courseservice.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author aldi
 * @since 06.06.2024
 */

@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class NotificationAspect {
    private final SubmissionRepository submissionRepository;

    private final NotificationClient notificationClient;
    private final UserClient userClient;

    @Async
    @AfterReturning(pointcut = "execution(* kz.edu.astanait.courseservice.service.SubmissionService.setGrade(..)) && args(id, dto)", returning = "result", argNames = "id,dto,result")
    public void sendGradeNotification(Long id, GradeDto dto, SubmissionDto result) {
        var submission = submissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Submission not found"));

        var user = userClient.findUserById(submission.getUploadedById()).getData();
        // skip if user has no tg or notification acceptance
        if (user.getReceiveTgNotification() == null || !user.getReceiveTgNotification() || user.getTgId() == null) {
            return;
        }

        String message = "Your submission has been graded. Grade: " + result.getGrade().getGrade();
        try {
            notificationClient.sendTgMessage(user.getTgId(), message);
            log.info("Notification sent successfully to user: {}", submission.getUploadedById());
        } catch (Exception e) {
            log.error("Failed to send notification to user: {}", submission.getUploadedById(), e);
        }
    }

}
