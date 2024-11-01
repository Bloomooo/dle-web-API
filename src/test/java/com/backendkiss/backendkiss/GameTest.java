package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Game;
import com.backendkiss.backendkiss.entity.GameMode;
import com.backendkiss.backendkiss.entity.PlayerGameData;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testId() {
        Long id = 1L;
        game.setId(id);
        assertThat(game.getId()).isEqualTo(id);
    }

    @Test
    void testGameMode() {
        GameMode gameMode = new GameMode();
        game.setGameMode(gameMode);
        assertThat(game.getGameMode()).isEqualTo(gameMode);
    }

    @Test
    void testStartTime() {
        String startTime = "2023-10-05T14:30:00Z";
        game.setStartTime(startTime);
        assertThat(game.getStartTime()).isEqualTo(startTime);
    }

    @Test
    void testOnGoing() {
        game.setOnGoing(true);
        assertThat(game.isOnGoing()).isTrue();

        game.setOnGoing(false);
        assertThat(game.isOnGoing()).isFalse();
    }

    @Test
    void testNbPlayer() {
        int nbPlayer = 4;
        game.setNbPlayer(nbPlayer);
        assertThat(game.getNbPlayer()).isEqualTo(nbPlayer);
    }

    @Test
    void testPlayerGameData() {
        List<PlayerGameData> playerGameDataList = new ArrayList<>();
        game.setPlayerGameData(playerGameDataList);
        assertThat(game.getPlayerGameData()).isEqualTo(playerGameDataList);
    }
}
