package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.GameRepository;

@Component
public class GameQueryResolver {

    private final GameRepository gameRepository;

    public GameQueryResolver(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
