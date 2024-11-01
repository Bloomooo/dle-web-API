package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.LevelBaseRepository;

@Component
public class LevelBaseQueryResolver {

    private final LevelBaseRepository levelBaseRepository;

    public LevelBaseQueryResolver(LevelBaseRepository levelBaseRepository) {
        this.levelBaseRepository = levelBaseRepository;
    }
}
