package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.Inventory;
import com.backendkiss.backendkiss.entity.Player;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testId() {
        inventory.setId(1);
        assertThat(inventory.getId()).isEqualTo(1);
    }

    @Test
    void testPlayer() {
        Player player = new Player();
        inventory.setPlayer(player);
        assertThat(inventory.getPlayer()).isEqualTo(player);
    }

    @Test
    void testCharacter() {
        Characters character = new Characters();
        inventory.setCharacter(character);
        assertThat(inventory.getCharacter()).isEqualTo(character);
    }
}
