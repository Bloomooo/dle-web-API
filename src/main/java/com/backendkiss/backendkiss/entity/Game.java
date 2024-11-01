package com.backendkiss.backendkiss.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.backendkiss.backendkiss.entity.interfaces.OutputEntity;

@Entity
@Table(name = "Game")
public class Game implements OutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_GAMEMODE", nullable = false)
    private GameMode gameMode;

    @Column(name = "START_TIME")
    private String startTime;

    @Column(name = "ON_GOING")
    private boolean onGoing;

    @Column(name = "NB_PLAYER")
    private int nbPlayer;

    @OneToMany(mappedBy = "game")
    private List<PlayerGameData> playerGameData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public int getNbPlayer() {
        return nbPlayer;
    }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public List<PlayerGameData> getPlayerGameData() {
        return playerGameData;
    }

    public void setPlayerGameData(List<PlayerGameData> playerGameData) {
        this.playerGameData = playerGameData;
    }
}
