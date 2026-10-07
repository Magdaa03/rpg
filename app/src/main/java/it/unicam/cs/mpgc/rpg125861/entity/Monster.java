package it.unicam.cs.mpgc.rpg125861.entity;

public class Monster {

    private final String name;
    private final int maxHealth;
    private int health;
    private final int damage;

    public Monster(String name, int maxHealth, int damage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void restoreHealth(int health) {
        if (health < 0 || health > maxHealth) {
            throw new IllegalArgumentException("Health value is out of range.");
        }

        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }
}
