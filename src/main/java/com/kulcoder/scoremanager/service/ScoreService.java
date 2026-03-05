package com.kulcoder.scoremanager.service;

import com.kulcoder.scoremanager.model.PlayerScore;
import com.kulcoder.scoremanager.repository.PlayerScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ScoreService {

    private final PlayerScoreRepository repository;

    public ScoreService(PlayerScoreRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void submitScore(String playerId, int score) {
        repository.upsertHighestScore(playerId, score);
    }

    @Transactional(readOnly = true)
    public List<PlayerScore> getLeaderboard(int limit) {
        return repository.getTopPlayers(limit);
    }

    @Transactional(readOnly = true)
    public List<PlayerScore> findMatches(String playerId, int currentScore, int matchCount) {
        return repository.getClosestMatches(currentScore, playerId, matchCount);
    }
}