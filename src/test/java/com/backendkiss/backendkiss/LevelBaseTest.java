package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.LevelBase;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LevelBaseTest {

    private LevelBase levelBase;

    @BeforeEach
    void setUp() {
        levelBase = new LevelBase();
    }

    @Test
    void testId() {
        levelBase.setId(1L);
        assertThat(levelBase.getId()).isEqualTo(1);
    }

    @Test
    void testLevel() {
        levelBase.setLevel(5);
        assertThat(levelBase.getLevel()).isEqualTo(5);
    }

    @Test
    void testXp() {
        levelBase.setXp(100);
        assertThat(levelBase.getXp()).isEqualTo(100);
    }

    @Test
    void testReward() {
        levelBase.setReward(50);
        assertThat(levelBase.getReward()).isEqualTo(50);
    }

    @Test
    void testConstructor() {
        LevelBase levelBaseWithArgs = new LevelBase(3, 150, 75);
        assertThat(levelBaseWithArgs.getLevel()).isEqualTo(3);
        assertThat(levelBaseWithArgs.getXp()).isEqualTo(150);
        assertThat(levelBaseWithArgs.getReward()).isEqualTo(75);
    }
}
