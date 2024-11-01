package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.PullHistoryRepository;

@Component
public class PullHistoryQueryResolver {

    private final PullHistoryRepository pullHistoryRepository;

    public PullHistoryQueryResolver(PullHistoryRepository pullHistoryRepository) {
        this.pullHistoryRepository = pullHistoryRepository;
    }
}
