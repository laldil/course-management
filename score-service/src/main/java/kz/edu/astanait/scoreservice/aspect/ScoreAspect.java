package kz.edu.astanait.scoreservice.aspect;

import kz.edu.astanait.scoreservice.dto.UpdateScoreRequest;
import kz.edu.astanait.scoreservice.models.ScoreEntity;
import kz.edu.astanait.scoreservice.models.ScoreLogEntity;
import kz.edu.astanait.scoreservice.repository.ScoreLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author aldi
 * @since 01.06.2024
 */

@RequiredArgsConstructor
@Component
@Aspect
public class ScoreAspect {

    private final ScoreLogRepository scoreLogRepository;

    @Pointcut("execution(* kz.edu.astanait.scoreservice.service.ScoreService.update(..)) && args(request)")
    public void updateScorePointcut(UpdateScoreRequest request) {
    }

    @AfterReturning(pointcut = "updateScorePointcut(request)", returning = "updatedScore", argNames = "request,updatedScore")
    public void logScoreUpdate(UpdateScoreRequest request, ScoreEntity updatedScore) {
        ScoreLogEntity log = new ScoreLogEntity();
        log.setScore(updatedScore);
        log.setCreatedDate(new Date());
        log.setTransactionType(request.getTransactionType());
        log.setAmount(request.getAmount());

        scoreLogRepository.save(log);
    }
}
