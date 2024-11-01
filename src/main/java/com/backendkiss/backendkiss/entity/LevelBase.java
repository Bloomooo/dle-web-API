package com.backendkiss.backendkiss.entity;

import com.backendkiss.backendkiss.entity.interfaces.OutputEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LevelBase implements OutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LEVEL")
    private int level;

    @Column(name = "XP")
    private int xp;

    @Column(name = "REWARD")
    private int reward;

    public LevelBase() {
    }

    public LevelBase(int level, int xp, int reward) {
        this.level = level;
        this.xp = xp;
        this.reward = reward;
    }

    public int getId() {
        return id.intValue();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
