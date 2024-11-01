package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.GameData;

public interface GameDataRepository extends JpaRepository<GameData, Integer> {

}
