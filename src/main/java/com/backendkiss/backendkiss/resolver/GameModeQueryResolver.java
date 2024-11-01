package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.GameModeRepository;

@Component
public class GameModeQueryResolver {

    private final GameModeRepository gameModeRepository;

    public GameModeQueryResolver(GameModeRepository gameModeRepository) {
        this.gameModeRepository = gameModeRepository;
    }
}
