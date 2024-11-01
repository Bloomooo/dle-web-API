package com.backendkiss.backendkiss.entity;

import com.backendkiss.backendkiss.entity.interfaces.OutputEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "Player")
public class Player implements OutputEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade =  CascadeType.REMOVE)
    @JoinColumn(name = "ID_USER", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "XP")
    private int xp;

    @Column(name = "LEVEL")
    private int level;

    @Column(name = "MONEY")
    private int money;

    @Column(name = "SKIN_MONEY")
    private int skinMoney;

    @Column(name = "SECURE")
    private boolean secure;

    @Column(name = "PITY_6")
    private int pity6;

    @Column(name = "PITY_5")
    private int pity5;

    @Column(name = "PITY_4")
    private int pity4;

    @Column(name = "ICON_ID_CHARACTER")
    private int iconIdCharacter;

    @Column(name = "BANNER_ID_CHARACTER")
    private int bannerIdCharacter;

    @Column(name = "ISDAILYDONE")
    private boolean isDailyDone;

    @Column(name = "ISHOURLYDONE")
    private boolean isHourlyDone;

    @OneToMany(mappedBy = "player")
    private List<Inventory> inventory;

    @OneToMany(mappedBy = "player")
    private List<PullHistory> pullHistory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSkinMoney() {
        return skinMoney;
    }

    public void setSkinMoney(int skinMoney) {
        this.skinMoney = skinMoney;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public int getPity6() {
        return pity6;
    }

    public void setPity6(int pity6) {
        this.pity6 = pity6;
    }

    public int getPity5() {
        return pity5;
    }

    public void setPity5(int pity5) {
        this.pity5 = pity5;
    }

    public int getPity4() {
        return pity4;
    }

    public void setPity4(int pity4) {
        this.pity4 = pity4;
    }

    public int getIconIdCharacter() {
        return iconIdCharacter;
    }

    public void setIconIdCharacter(int iconIdCharacter) {
        this.iconIdCharacter = iconIdCharacter;
    }

    public int getBannerIdCharacter() {
        return bannerIdCharacter;
    }

    public void setBannerIdCharacter(int bannerIdCharacter) {
        this.bannerIdCharacter = bannerIdCharacter;
    }

    public boolean isDailyDone() {
        return isDailyDone;
    }

    public void setDailyDone(boolean dailyDone) {
        isDailyDone = dailyDone;
    }

    public boolean isHourlyDone() {
        return isHourlyDone;
    }

    public void setHourlyDone(boolean hourlyDone) {
        isHourlyDone = hourlyDone;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public List<PullHistory> getPullHistory() {
        return pullHistory;
    }

    public void setPullHistory(List<PullHistory> pullHistory) {
        this.pullHistory = pullHistory;
    }
}
