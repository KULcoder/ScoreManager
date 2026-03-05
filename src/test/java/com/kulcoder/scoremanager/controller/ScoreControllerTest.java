package com.kulcoder.scoremanager.controller;

import com.kulcoder.scoremanager.model.PlayerScore;
import com.kulcoder.scoremanager.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ScoreController.class)
@ActiveProfiles("test")
public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScoreService scoreService;

    @Test
    public void testSubmitScore() throws Exception {
        mockMvc.perform(post("/api/scores/player1")
                .param("score", "100"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLeaderboard() throws Exception {
        PlayerScore player1 = new PlayerScore("player1", 100);
        PlayerScore player2 = new PlayerScore("player2", 90);
        List<PlayerScore> leaderboard = Arrays.asList(player1, player2);

        when(scoreService.getLeaderboard(2)).thenReturn(leaderboard);

        mockMvc.perform(get("/api/scores/leaderboard")
                .param("limit", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].playerId", is("player1")))
                .andExpect(jsonPath("$[0].score", is(100)))
                .andExpect(jsonPath("$[1].playerId", is("player2")))
                .andExpect(jsonPath("$[1].score", is(90)));
    }

    @Test
    public void testGetMatches() throws Exception {
        PlayerScore player1 = new PlayerScore("player2", 105);
        List<PlayerScore> matches = List.of(player1);

        when(scoreService.findMatches("player1", 100, 1)).thenReturn(matches);

        mockMvc.perform(get("/api/scores/match/player1")
                .param("score", "100")
                .param("limit", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].playerId", is("player2")))
                .andExpect(jsonPath("$[0].score", is(105)));
    }

    @Test
    public void testSubmitScore_InvalidScore() throws Exception {
        mockMvc.perform(post("/api/scores/player1")
                .param("score", "0"))
                .andExpect(status().isBadRequest());
    }
}
