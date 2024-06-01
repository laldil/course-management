package kz.edu.astanait.courseservice.client;

import kz.edu.astanait.courseservice.dto.ScoreDto;
import kz.edu.astanait.courseservice.dto.UpdateScoreRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author aldi
 * @since 02.06.2024
 */
@FeignClient(name = "score-service", url = "${application.config.url.score-service}")
public interface ScoreClient {
    @PatchMapping("/update")
    ScoreDto update(@RequestBody UpdateScoreRequest request);
}
