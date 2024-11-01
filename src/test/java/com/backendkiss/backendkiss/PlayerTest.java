package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Inventory;
import com.backendkiss.backendkiss.entity.Player;
import com.backendkiss.backendkiss.entity.PullHistory;
import com.backendkiss.backendkiss.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testId() {
        player.setId(1);
        assertThat(player.getId()).isEqualTo(1);
    }

    @Test
    void testUser() {
        User user = new User();
        player.setUser(user);
        assertThat(player.getUser()).isEqualTo(user);
    }

    @Test
    void testXp() {
        player.setXp(100);
        assertThat(player.getXp()).isEqualTo(100);
    }

    @Test
    void testLevel() {
        player.setLevel(10);
        assertThat(player.getLevel()).isEqualTo(10);
    }

    @Test
    void testMoney() {
        player.setMoney(200);
        assertThat(player.getMoney()).isEqualTo(200);
    }

    @Test
    void testSkinMoney() {
        player.setSkinMoney(50);
        assertThat(player.getSkinMoney()).isEqualTo(50);
    }

    @Test
    void testSecure() {
        player.setSecure(true);
        assertThat(player.isSecure()).isTrue();
    }

    @Test
    void testPityB() {
        player.setPity6(5);
        assertThat(player.getPity6()).isEqualTo(5);
    }

    @Test
    void testPityS() {
        player.setPity5(3);
        assertThat(player.getPity5()).isEqualTo(3);
    }

    @Test
    void testPityF() {
        player.setPity4(1);
        assertThat(player.getPity4()).isEqualTo(1);
    }

    @Test
    void testIconIdCharacter() {
        player.setIconIdCharacter(10);
        assertThat(player.getIconIdCharacter()).isEqualTo(10);
    }

    @Test
    void testBannerIdCharacter() {
        player.setBannerIdCharacter(20);
        assertThat(player.getBannerIdCharacter()).isEqualTo(20);
    }

    @Test
    void testDailyDone() {
        player.setDailyDone(true);
        assertThat(player.isDailyDone()).isTrue();
    }

    @Test
    void testHourlyDone() {
        player.setHourlyDone(false);
        assertThat(player.isHourlyDone()).isFalse();
    }

    @Test
    void testInventory() {
        List<Inventory> inventory = new ArrayList<>();
        player.setInventory(inventory);
        assertThat(player.getInventory()).isEqualTo(inventory);
    }

    @Test
    void testPullHistory() {
        List<PullHistory> pullHistory = new ArrayList<>();
        player.setPullHistory(pullHistory);
        assertThat(player.getPullHistory()).isEqualTo(pullHistory);
    }
}
