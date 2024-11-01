package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.GameDataRepository;

@Component
public class GameDataQueryResolver {

    private final GameDataRepository gameDataRepository;

    public GameDataQueryResolver(GameDataRepository gameDataRepository) {
        this.gameDataRepository = gameDataRepository;
    }
}
