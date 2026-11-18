package it.unicam.cs.mpgc.rpg125861.controller;

import it.unicam.cs.mpgc.rpg125861.entity.Hero;
import it.unicam.cs.mpgc.rpg125861.entity.Monster;
import it.unicam.cs.mpgc.rpg125861.service.CombatService;
import it.unicam.cs.mpgc.rpg125861.service.GameService;
import it.unicam.cs.mpgc.rpg125861.persistence.GameSaveData;

import java.util.List;

public class GameFactory {

    private static List<Monster> createMonsters() {
        return List.of(
                new Monster("Goblin", 12, 2),
                new Monster("Orc", 18, 2),
                new Monster("Dragon", 25, 2)
        );
    }

    public static GameController newGame(String heroName) {
        Hero hero = new Hero(heroName);
        GameService gameService = new GameService(hero, createMonsters());
        CombatService combatService = new CombatService();

        return new GameController(gameService, combatService);
    }

    public static GameController loadGame(GameSaveData saveData) {
        List<Monster> monsters = createMonsters();
        validateSaveData(saveData, monsters.size());

        Hero hero = createHero(saveData);
        restoreMonsterHealths(monsters, saveData.getMonsterHealths());

        GameService gameService = new GameService(hero, monsters);
        restoreCurrentMonster(gameService, saveData.getCurrentMonsterIndex());

        return new GameController(gameService, new CombatService());
    }

    private static Hero createHero(GameSaveData saveData) {
        Hero hero = new Hero(saveData.getHeroName());
        hero.restoreHealth(saveData.getHeroHealth());
        hero.restoreRankProgress(
                saveData.getHeroRank(), saveData.getHeroRankProgress()
        );
        return hero;
    }

    private static void restoreMonsterHealths(
            List<Monster> monsters,
            List<Integer> savedHealths
    ) {
        for (int i = 0; i < monsters.size(); i++) {
            Integer health = savedHealths.get(i);

            if (health == null) {
                throw new IllegalArgumentException(
                        "A monster health value is missing."
                );
            }

            monsters.get(i).restoreHealth(health);
        }
    }

    private static void restoreCurrentMonster(
            GameService gameService,
            int monsterIndex
    ) {
        for (int i = 0; i < monsterIndex; i++) {
            gameService.moveToNextMonster();
        }
    }

    private static void validateSaveData(
            GameSaveData saveData,
            int monsterCount
    ) {
        if (saveData == null) {
            throw new IllegalArgumentException("Save data is missing.");
        }

        if (saveData.getHeroName() == null
                || saveData.getHeroName().isBlank()) {
            throw new IllegalArgumentException("Hero name is missing.");
        }

        List<Integer> savedHealths = saveData.getMonsterHealths();

        if (savedHealths == null || savedHealths.size() != monsterCount) {
            throw new IllegalArgumentException(
                    "The saved monster data is incomplete."
            );
        }

        int monsterIndex = saveData.getCurrentMonsterIndex();

        if (monsterIndex < 0 || monsterIndex > monsterCount) {
            throw new IllegalArgumentException(
                    "The saved monster index is invalid."
            );
        }
    }
}
