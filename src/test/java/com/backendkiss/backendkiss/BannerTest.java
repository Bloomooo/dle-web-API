package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Banner;
import com.backendkiss.backendkiss.entity.LootTable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BannerTest {

    private Banner banner;

    @BeforeEach
    void setUp() {
        banner = new Banner();
    }

    @Test
    void testId() {
        int id = 1;
        banner.setId(id);
        assertThat(banner.getId()).isEqualTo(id);
    }

    @Test
    void testLootTable() {
        LootTable lootTable = new LootTable();
        banner.setLootTable(lootTable);
        assertThat(banner.getLootTable()).isEqualTo(lootTable);
    }
}
