package it.unicam.cs.mpgc.rpg125861.service;

import it.unicam.cs.mpgc.rpg125861.entity.Hero;

public class RankProgressionService {

    private final int progressRequired;

    public RankProgressionService() {
        this(3);
    }

    public RankProgressionService(int progressRequired) {
        if (progressRequired < 1) {
            throw new IllegalArgumentException(
                    "Progress required must be positive."
            );
        }
        this.progressRequired = progressRequired;
    }

    public boolean recordMonsterDefeat(Hero hero) {
        int rank = hero.getRank();
        int progress = hero.getRankProgress() + 1;
        boolean rankUp = progress >= progressRequired;

        if (rankUp) {
            rank++;
            progress -= progressRequired;
        }

        hero.updateRankProgress(rank, progress);
        return rankUp;
    }

    public void restoreProgress(Hero hero, int rank, int progress) {
        if (rank < 1 || progress < 0 || progress >= progressRequired) {
            throw new IllegalArgumentException("Rank progress is invalid.");
        }
        hero.updateRankProgress(rank, progress);
    }

    public int getProgressRequired() {
        return progressRequired;
    }
}
