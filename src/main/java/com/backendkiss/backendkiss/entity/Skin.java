package com.backendkiss.backendkiss.entity;

import com.backendkiss.backendkiss.entity.interfaces.OutputEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SKIN")
public class Skin extends Lootable implements OutputEntity {

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID", nullable = true)
    @JsonBackReference
    private Characters character;

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getRarity() {
        return super.getRarity();
    }

    @Override
    public void setRarity(int rarity) {
        super.setRarity(rarity);
    }

    @Override
    public Media getMedia() {
        return super.getMedia();
    }

    @Override
    public void setMedia(Media media) {
        super.setMedia(media);
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }
}
