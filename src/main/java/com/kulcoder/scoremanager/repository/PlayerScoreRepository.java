package com.kulcoder.scoremanager.repository;

import com.kulcoder.scoremanager.model.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, String> {

    // 1. Insert (Upsert for Highest Score)
    @Modifying
    @Query(value = """
        INSERT INTO player_scores (player_id, score, updated_at) 
        VALUES (:playerId, :score, CURRENT_TIMESTAMP) 
        ON CONFLICT (player_id) DO UPDATE 
        SET score = EXCLUDED.score, updated_at = CURRENT_TIMESTAMP 
        WHERE EXCLUDED.score > player_scores.score
            """, nativeQuery = true)
    void upsertHighestScore(@Param("playerId") String playerId, @Param("score") int score);

    // 2. Leaderboard
    @Query(value = "SELECT * FROM player_scores ORDER BY score DESC LIMIT :limit", nativeQuery = true)
    List<PlayerScore> getTopPlayers(@Param("limit") int limit);

    // 3. Player Matching (Simplified)
    @Query(value = """
        SELECT * FROM (
            (
                SELECT * FROM player_scores
                WHERE score >= :targetScore AND player_id != :targetId
                ORDER BY score ASC
                LIMIT :limit
            )
            UNION ALL
            (
                SELECT * FROM player_scores
                WHERE score < :targetScore AND player_id != :targetId
                ORDER BY score DESC
                LIMIT :limit
            )
        ) AS combined
        ORDER BY ABS(combined.score - :targetScore) ASC
        LIMIT :limit
            """, nativeQuery = true)
    List<PlayerScore> getClosestMatches(@Param("targetScore") int targetScore, @Param("targetId") String targetId, @Param("limit") int limit);
}
