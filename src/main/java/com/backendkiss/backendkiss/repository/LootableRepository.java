package com.backendkiss.backendkiss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backendkiss.backendkiss.entity.Lootable;

public interface LootableRepository extends JpaRepository<Lootable, Integer> {
    @Query("SELECT l FROM Lootable l WHERE TYPE(l) = :type")
    List<Lootable> findAllByType(Class<?> type);

    @Query("SELECT l FROM Lootable l WHERE TYPE(l) = :type AND l.name = :name")
    Lootable findByNameAndType(String name, Class<?> type);
}
