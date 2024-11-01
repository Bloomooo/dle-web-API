package com.backendkiss.backendkiss.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

import com.backendkiss.backendkiss.entity.interfaces.OutputEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("CHARACTER")
public class Characters extends Lootable implements OutputEntity {

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Skin skin;

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

    public Media getMedia() {
        return super.getMedia();
    }

    public void setMedia(Media media) {
        super.setMedia(media);
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkins(Skin skin) {
        this.skin = skin;
    }

    @Override
    public String getCardSplashartString() {
        return super.getCardSplashartString();
    }

    @Override
    public String getBannerSplashartString() {
        return super.getBannerSplashartString();
    }

    @Override
    public void setCardSplashartString(String cardSplashartString) {
        super.setCardSplashartString(cardSplashartString);
    }

    @Override
    public void setBannerSplashartString(String bannerSplashartString) {
        super.setBannerSplashartString(bannerSplashartString);
    }
}
