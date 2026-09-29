package it.unicam.cs.mpgc.rpg125861.service;

import it.unicam.cs.mpgc.rpg125861.entity.Hero;
import it.unicam.cs.mpgc.rpg125861.entity.Monster;

import java.util.Random;

public class CombatService {

    private final Random random;

    public CombatService() {
        this.random = new Random();
    }

    public void quickAttack(Monster monster) {
        monster.takeDamage(5);
    }

    public boolean powerfulAttack(Monster monster) {
        if (random.nextDouble() < 0.7) {
            monster.takeDamage(10);
            return true;
        }

        return false;
    }

    public void monsterAttack(Hero hero, Monster monster) {
        hero.takeDamage(monster.getDamage());
    }
}