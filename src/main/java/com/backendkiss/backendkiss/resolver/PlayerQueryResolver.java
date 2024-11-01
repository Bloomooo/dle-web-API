package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.PlayerRepository;

@Component
public class PlayerQueryResolver {

    private final PlayerRepository playerRepository;

    public PlayerQueryResolver(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
}
