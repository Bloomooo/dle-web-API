package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.LootTable;
import com.backendkiss.backendkiss.entity.Skin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LootTableTest {

    private LootTable lootTable;

    @BeforeEach
    void setUp() {
        lootTable = new LootTable();
    }

    @Test
    void testId() {
        lootTable.setId(1);
        assertThat(lootTable.getId()).isEqualTo(1);
    }

    @Test
    void testName() {
        lootTable.setName("Epic Loot");
        assertThat(lootTable.getName()).isEqualTo("Epic Loot");
    }

    @Test
    void testRarity() {
        lootTable.setRarity("Rare");
        assertThat(lootTable.getRarity()).isEqualTo("Rare");
    }

    @Test
    void testLimited() {
        lootTable.setLimited(true);
        assertThat(lootTable.isLimited()).isTrue();
    }

    @Test
    void testSkins() {
        List<Skin> skins = new ArrayList<>();
        lootTable.setSkins(skins);
        assertThat(lootTable.getSkins()).isEqualTo(skins);
    }
}
