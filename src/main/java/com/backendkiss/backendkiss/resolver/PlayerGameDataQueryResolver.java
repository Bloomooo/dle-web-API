package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.PlayerGameDataRepository;

@Component
public class PlayerGameDataQueryResolver {

    private final PlayerGameDataRepository playerGameDataRepository;

    public PlayerGameDataQueryResolver(PlayerGameDataRepository playerGameDataRepository) {
        this.playerGameDataRepository = playerGameDataRepository;
    }
}
