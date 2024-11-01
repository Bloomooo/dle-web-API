package com.backendkiss.backendkiss.entity;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "Loot")
public class Lootable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected int id;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "RARITY", nullable = false)
    protected int rarity;

    @Column(name = "LIMITED", nullable = false)
    protected boolean limited;

    @Column(name = "SPLASHART_CARD", nullable = true)
    @Lob
    protected Blob cardSplashartBlob;

    @Column(name = "SPLASHART_BANNER", nullable = true)
    @Lob
    protected Blob bannerSplashartBlob;

    @ManyToOne
    @JoinColumn(name = "ID_MEDIA", nullable = false)
    private Media media;

    @Transient
    private byte[] cardSplashartBytes;

    @Transient
    private byte[] bannerSplashartBytes;

    @Transient
    private String cardSplashartString;

    @Transient
    private String bannerSplashartString;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }

    public Blob getCardSplashartBlob() {
        return cardSplashartBlob;
    }

    public void setCardSplashartBlob(Blob cardSplashart) {
        this.cardSplashartBlob = cardSplashart;
    }

    public Blob getBannerSplashartBlob() {
        return bannerSplashartBlob;
    }

    public void setBannerSplashartBlob(Blob bannerSplashart) {
        this.bannerSplashartBlob = bannerSplashart;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * @return byte[] return the cardSplashartBytes
     */
    public byte[] getCardSplashartBytes() {
        return cardSplashartBytes;
    }

    /**
     * @param cardSplashartBytes the cardSplashartBytes to set
     */
    public void setCardSplashartBytes(byte[] cardSplashartBytes) {
        this.cardSplashartBytes = cardSplashartBytes;
    }

    /**
     * @return byte[] return the bannerSplashartBytes
     */
    public byte[] getBannerSplashartBytes() {
        return bannerSplashartBytes;
    }

    /**
     * @param bannerSplashartBytes the bannerSplashartBytes to set
     */
    public void setBannerSplashartBytes(byte[] bannerSplashartBytes) {
        this.bannerSplashartBytes = bannerSplashartBytes;
    }

    /**
     * @return String return the cardSplashartString
     */
    public String getCardSplashartString() {
        return cardSplashartString;
    }

    /**
     * @param cardSplashartString the cardSplashartString to set
     */
    public void setCardSplashartString(String cardSplashartString) {
        this.cardSplashartString = cardSplashartString;
    }

    /**
     * @return String return the bannerSplashartString
     */
    public String getBannerSplashartString() {
        return bannerSplashartString;
    }

    /**
     * @param bannerSplashartString the bannerSplashartString to set
     */
    public void setBannerSplashartString(String bannerSplashartString) {
        this.bannerSplashartString = bannerSplashartString;
    }

}
