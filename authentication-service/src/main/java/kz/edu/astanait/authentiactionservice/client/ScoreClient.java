package kz.edu.astanait.authentiactionservice.client;

import kz.edu.astanait.authentiactionservice.dto.ScoreDto;
import kz.edu.astanait.authentiactionservice.dto.UpdateScoreRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author aldi
 * @since 02.06.2024
 */

@FeignClient(name = "score-service", url = "${application.config.url.score-service}")
public interface ScoreClient {
    @GetMapping("/{userId}")
    ScoreDto getByUserId(@PathVariable Long userId);

    @PostMapping("/{userId}/create")
    ScoreDto create(@PathVariable Long userId);

    @GetMapping("/list")
    List<ScoreDto> getList(@RequestParam List<Long> userIds);

    @PatchMapping("/update")
    ScoreDto update(@RequestBody UpdateScoreRequest request);
}
