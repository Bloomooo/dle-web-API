package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
