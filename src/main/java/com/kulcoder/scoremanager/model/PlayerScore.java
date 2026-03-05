package com.kulcoder.scoremanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table(name = "player_scores")
public class PlayerScore {
    @Id
    @Column(name = "player_id")
    private String playerId;

    private int score;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    public PlayerScore() {
    }

    public PlayerScore(String newPlayerId, int newScore) {
        this.playerId = newPlayerId;
        this.score = newScore;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String newPlayerId) {
        this.playerId = newPlayerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime newUpdatedAt) {
        this.updatedAt = newUpdatedAt;
    }
}
