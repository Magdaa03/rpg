package it.unicam.cs.mpgc.rpg125861.persistence;

import java.util.ArrayList;
import java.util.List;

public class GameSaveData {

    private String heroName;
    private int heroHealth;
    private List<Integer> monsterHealths = new ArrayList<>();
    private int currentMonsterIndex;

    public GameSaveData() {
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getHeroHealth() {
        return heroHealth;
    }

    public void setHeroHealth(int heroHealth) {
        this.heroHealth = heroHealth;
    }

    public List<Integer> getMonsterHealths() {
        return monsterHealths;
    }

    public void setMonsterHealths(List<Integer> monsterHealths) {
        this.monsterHealths = new ArrayList<>(monsterHealths);
    }

    public int getCurrentMonsterIndex() {
        return currentMonsterIndex;
    }

    public void setCurrentMonsterIndex(int currentMonsterIndex) {
        this.currentMonsterIndex = currentMonsterIndex;
    }
}