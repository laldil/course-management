package kz.edu.astanait.scoreservice.service;

import kz.edu.astanait.scoreservice.client.UserClient;
import kz.edu.astanait.scoreservice.dto.ScoreDto;
import kz.edu.astanait.scoreservice.dto.UpdateScoreRequest;
import kz.edu.astanait.scoreservice.dto.UserDto;
import kz.edu.astanait.scoreservice.models.ScoreEntity;
import kz.edu.astanait.scoreservice.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author aldi
 * @since 01.06.2024
 */

@RequiredArgsConstructor
@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

    private final UserClient userClient;

    public ScoreEntity getByUser(Long userId) {
        return scoreRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ScoreEntity create(Long userId) {
        scoreRepository.findByUserId(userId).ifPresent(score -> {
            throw new RuntimeException("Score for user already exists");
        });

        var score = new ScoreEntity(userId);
        return scoreRepository.save(score);
    }

    public List<ScoreEntity> getByUserList(List<Long> userIds) {
        return userIds.stream().map(this::getByUser).toList();
    }

    public ScoreEntity update(UpdateScoreRequest request) {
        var score = getByUser(request.getUserId());
        var calculatedScore = ScoreCalculator.calculateScore(score.getCurrentScore(), request.getAmount(), request.getTransactionType());
        var calculatedAllTimeScore = ScoreCalculator.calculateAllTimeScore(score.getCurrentScore(), request.getAmount(), request.getTransactionType());

        score.setCurrentScore(calculatedScore);
        score.setAllTimeScore(calculatedAllTimeScore);

        return scoreRepository.save(score);
    }

    public List<ScoreDto> getScoreTop() {
        var top = scoreRepository.findTop20ByOrderByAllTimeScoreDesc();

        var userIds = top.stream().map(ScoreEntity::getUserId).toList();
        var users = userClient.findUsersByIdIn(userIds).getList();
        Map<Long, UserDto> userMap = users.stream()
                .collect(Collectors.toMap(UserDto::getId, user -> user));

        return top.stream()
                .map(score -> new ScoreDto(userMap.get(score.getUserId()), score.getAllTimeScore()))
                .toList();
    }
}
