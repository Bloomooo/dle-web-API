package com.backendkiss.backendkiss.resolver;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.entity.Skin;
import com.backendkiss.backendkiss.repository.LootableRepository;

@Component
public class SkinQueryResolver {

    private final LootableRepository lootableRepository;

    public SkinQueryResolver(LootableRepository lootableRepository) {
        this.lootableRepository = lootableRepository;
    }

    public List<Skin> getAllSkins() {
        return lootableRepository.findAllByType(Skin.class).stream()
                .map(obj -> (Skin) obj)
                .collect(Collectors.toList());
    }
}
