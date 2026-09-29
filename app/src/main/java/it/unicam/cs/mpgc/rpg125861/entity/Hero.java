package it.unicam.cs.mpgc.rpg125861.entity;


    public class Hero {

        private final String name;
        private final int maxHealth;
        private int health;

        public Hero(String name) {
            this.name = name;
            this.maxHealth = 30;
            this.health = maxHealth;
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public int getMaxHealth() {
            return maxHealth;
        }

        public void takeDamage(int damage) {
            health -= damage;

            if (health < 0) {
                health = 0;
            }
        }
    }

