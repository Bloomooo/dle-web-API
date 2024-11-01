package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.InventoryRepository;

@Component
public class InventoryQueryResolver {

    private final InventoryRepository inventoryRepository;

    public InventoryQueryResolver(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
}
