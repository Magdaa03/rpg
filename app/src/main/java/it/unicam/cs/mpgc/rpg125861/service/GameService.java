package it.unicam.cs.mpgc.rpg125861.service;

import it.unicam.cs.mpgc.rpg125861.entity.Hero;
import it.unicam.cs.mpgc.rpg125861.entity.Monster;

import java.util.List;

public class GameService {

    private final Hero hero;
    private final List<Monster> monsters;
    private int currentMonsterIndex;

    public GameService(Hero hero, List<Monster> monsters) {
        this.hero = hero;
        this.monsters = monsters;
        this.currentMonsterIndex = 0;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getCurrentMonster() {
        return monsters.get(currentMonsterIndex);
    }
    public List<Monster> getMonsters() {
        return List.copyOf(monsters);
    }

    public int getCurrentMonsterIndex() {
        return currentMonsterIndex;
    }

    public boolean recordMonsterDefeat() {
        return hero.addRankProgress();
    }
    public boolean isVictory() {
        return currentMonsterIndex >= monsters.size();
    }

    public boolean isDefeat() {
        return hero.getHealth() <= 0;
    }

    public boolean isGameOver() {
        return isVictory() || isDefeat();
    }

    public void moveToNextMonster() {
        if (currentMonsterIndex < monsters.size()) {
            currentMonsterIndex++;
        }

    }
    public void checkMonsterDefeated() {
        if (currentMonsterIndex < monsters.size()
                && getCurrentMonster().getHealth() <= 0) {
            moveToNextMonster();
        }
    }
}
