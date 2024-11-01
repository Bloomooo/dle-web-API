package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
