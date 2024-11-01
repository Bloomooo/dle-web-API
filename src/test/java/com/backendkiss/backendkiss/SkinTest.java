package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.LootTable;
import com.backendkiss.backendkiss.entity.Skin;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SkinTest {

    private Skin skin;

    @BeforeEach
    void setUp() {
        skin = new Skin();
    }

    @Test
    void testId() {
        skin.setId(1);
        assertThat(skin.getId()).isEqualTo(1);
    }

    @Test
    void testCharacter() {
        Characters character = new Characters();
        skin.setCharacter(character);
        assertThat(skin.getCharacter()).isEqualTo(character);
    }

    @Test
    void testLootTable() {
        LootTable lootTable = new LootTable();
        skin.setLootTable(lootTable);
        assertThat(skin.getLootTable()).isEqualTo(lootTable);
    }
}
