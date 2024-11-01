package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.PullHistory;

public interface PullHistoryRepository extends JpaRepository<PullHistory, Integer> {

}
