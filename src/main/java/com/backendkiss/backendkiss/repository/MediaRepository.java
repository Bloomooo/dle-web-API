package com.backendkiss.backendkiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendkiss.backendkiss.entity.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Media findByName(String name);
}
