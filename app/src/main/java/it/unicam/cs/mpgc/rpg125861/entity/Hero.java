package it.unicam.cs.mpgc.rpg125861.entity;


    public class Hero {

        private final String name;
        private final int maxHealth;
        private int health;
        private int rank = 1;
        private int rankProgress;

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

        public void restoreHealth(int health) {
            if (health < 0 || health > maxHealth) {
                throw new IllegalArgumentException("Health value is out of range.");
            }

            this.health = health;
        }

        public int getMaxHealth() {
            return maxHealth;
        }

        public int getRank() {
            return rank;
        }

        public int getRankProgress() {
            return rankProgress;
        }

        public void updateRankProgress(int rank, int rankProgress) {
            if (rank < 1 || rankProgress < 0) {
                throw new IllegalArgumentException("Rank progress is invalid.");
            }
            this.rank = rank;
            this.rankProgress = rankProgress;
        }

        public void takeDamage(int damage) {
            health -= damage;

            if (health < 0) {
                health = 0;
            }
        }
    }

