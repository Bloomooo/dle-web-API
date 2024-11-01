package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Game;
import com.backendkiss.backendkiss.entity.GameData;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GameDataTest {

    private GameData gameData;

    @BeforeEach
    void setUp() {
        gameData = new GameData();
    }

    @Test
    void testId() {
        Long id = 1L;
        gameData.setId(id);
        assertThat(gameData.getId()).isEqualTo(id);
    }

    @Test
    void testGame() {
        Game game = new Game();
        gameData.setGame(game);
        assertThat(gameData.getGame()).isEqualTo(game);
    }

    @Test
    void testAnswer() {
        String answer = "42";
        gameData.setAnswer(answer);
        assertThat(gameData.getAnswer()).isEqualTo(answer);
    }

    @Test
    void testAdditionalAnswers() {
        String additionalAnswers = "43, 44";
        gameData.setAdditionalAnswers(additionalAnswers);
        assertThat(gameData.getAdditionalAnswers()).isEqualTo(additionalAnswers);
    }

    @Test
    void testAlreadyPlayed() {
        gameData.setAlreadyPlayed(true);
        assertThat(gameData.isAlreadyPlayed()).isTrue();

        gameData.setAlreadyPlayed(false);
        assertThat(gameData.isAlreadyPlayed()).isFalse();
    }

    @Test
    void testStartTime() {
        String startTime = "2023-10-05T14:30:00Z";
        gameData.setStartTime(startTime);
        assertThat(gameData.getStartTime()).isEqualTo(startTime);
    }
}
