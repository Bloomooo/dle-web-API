package com.backendkiss.backendkiss;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Game;
import com.backendkiss.backendkiss.entity.Player;
import com.backendkiss.backendkiss.entity.PlayerGameData;

@SpringBootTest
class PlayerGameDataTest {

    private PlayerGameData playerGameData;

    @BeforeEach
    void setUp() {
        playerGameData = new PlayerGameData();
    }

    @Test
    void testId() {
        playerGameData.setId(1);
        assertThat(playerGameData.getId()).isEqualTo(1);
    }

    @Test
    void testGame() {
        Game game = new Game();
        playerGameData.setGame(game);
        assertThat(playerGameData.getGame()).isEqualTo(game);
    }

    @Test
    void testPlayer() {
        Player player = new Player();
        playerGameData.setPlayer(player);
        assertThat(playerGameData.getPlayer()).isEqualTo(player);
    }

    @Test
    void testCorrect() {
        playerGameData.setCorrect(true);
        assertThat(playerGameData.isCorrect()).isTrue();
    }

    @Test
    void testSkipped() {
        playerGameData.setSkipped(false);
        assertThat(playerGameData.isSkipped()).isFalse();
    }

    @Test
    void testNbCorrect() {
        playerGameData.setNbCorrect(5);
        assertThat(playerGameData.getNbCorrect()).isEqualTo(5);
    }
}
