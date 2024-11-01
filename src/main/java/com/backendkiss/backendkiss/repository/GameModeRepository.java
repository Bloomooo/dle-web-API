package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.GameMode;

public interface GameModeRepository extends JpaRepository<GameMode, Integer> {

}
