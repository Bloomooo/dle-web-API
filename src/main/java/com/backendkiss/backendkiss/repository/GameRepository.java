package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

}