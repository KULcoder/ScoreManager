CREATE TABLE player_scores (
                               player_id VARCHAR(50) PRIMARY KEY,
                               score INT NOT NULL,
                               updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_player_scores_score_desc ON player_scores (score DESC);