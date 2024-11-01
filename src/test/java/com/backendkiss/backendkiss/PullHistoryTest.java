package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.LootTable;
import com.backendkiss.backendkiss.entity.Player;
import com.backendkiss.backendkiss.entity.PullHistory;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PullHistoryTest {

    private PullHistory pullHistory;

    @BeforeEach
    void setUp() {
        pullHistory = new PullHistory();
    }

    @Test
    void testId() {
        pullHistory.setId(1);
        assertThat(pullHistory.getId()).isEqualTo(1);
    }

    @Test
    void testPlayer() {
        Player player = new Player();
        pullHistory.setPlayer(player);
        assertThat(pullHistory.getPlayer()).isEqualTo(player);
    }

    @Test
    void testLootTable() {
        LootTable lootTable = new LootTable();
        pullHistory.setLootTable(lootTable);
        assertThat(pullHistory.getLootTable()).isEqualTo(lootTable);
    }
}
