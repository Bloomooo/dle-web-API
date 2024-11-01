package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Game;
import com.backendkiss.backendkiss.entity.GameMode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GameModeTest {

    private GameMode gameMode;

    @BeforeEach
    void setUp() {
        gameMode = new GameMode();
    }

    @Test
    void testId() {
        gameMode.setId(1);
        assertThat(gameMode.getId()).isEqualTo(1);
    }

    @Test
    void testName() {
        String name = "Survival";
        gameMode.setName(name);
        assertThat(gameMode.getName()).isEqualTo(name);
    }

    @Test
    void testStatus() {
        gameMode.setStatus(true);
        assertThat(gameMode.isStatus()).isTrue();

        gameMode.setStatus(false);
        assertThat(gameMode.isStatus()).isFalse();
    }

    @Test
    void testImg() {
        byte[] img = new byte[] { 1, 2, 3 };
        gameMode.setImg(img);
        assertThat(gameMode.getImg()).isEqualTo(img);
    }

    @Test
    void testBaseReward() {
        gameMode.setBaseReward(100);
        assertThat(gameMode.getBaseReward()).isEqualTo(100);
    }

    @Test
    void testGames() {
        List<Game> games = new ArrayList<>();
        gameMode.setGames(games);
        assertThat(gameMode.getGames()).isEqualTo(games);
    }
}
