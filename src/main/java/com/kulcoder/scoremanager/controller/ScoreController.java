package com.kulcoder.scoremanager.controller;

import com.kulcoder.scoremanager.model.PlayerScore;
import com.kulcoder.scoremanager.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/scores")
@Validated
// TODO: Add security to protect the endpoints
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/{playerId}")
    public ResponseEntity<Void> submitScore(@PathVariable @NotBlank String playerId, @RequestParam @Min(1) int score) {
        scoreService.submitScore(playerId, score);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<PlayerScore>> getLeaderboard(@RequestParam(defaultValue = "10") @Min(1) int limit) {
        return ResponseEntity.ok(scoreService.getLeaderboard(limit));
    }

    @GetMapping("/match/{playerId}")
    public ResponseEntity<List<PlayerScore>> getMatches(
            @PathVariable @NotBlank String playerId,
            @RequestParam @Min(1) int score,
            @RequestParam(defaultValue = "10") @Min(1) int limit) {
        return ResponseEntity.ok(scoreService.findMatches(playerId, score, limit));
    }
}
