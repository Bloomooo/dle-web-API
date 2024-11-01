package com.backendkiss.backendkiss.services;

import org.springframework.stereotype.Service;

import com.backendkiss.backendkiss.entity.Player;
import com.backendkiss.backendkiss.entity.User;
import com.backendkiss.backendkiss.repository.PlayerRepository;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public boolean createPlayer(User user) {
        try {
            if (user == null) {
                return false;
            }
            Player player = new Player();
            player.setUser(user);
            player.setDailyDone(false);
            player.setHourlyDone(false);
            player.setLevel(1);
            player.setMoney(0);
            player.setSkinMoney(0);
            player.setXp(0);
            player.setSecure(false);
            player.setPity6(0);
            player.setPity5(0);
            player.setPity4(0);
            playerRepository.save(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
