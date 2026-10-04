package it.unicam.cs.mpgc.rpg125861.controller;

import it.unicam.cs.mpgc.rpg125861.service.CombatService;
import it.unicam.cs.mpgc.rpg125861.service.GameService;
import it.unicam.cs.mpgc.rpg125861.entity.Hero;
import it.unicam.cs.mpgc.rpg125861.entity.Monster;

import java.util.List;

public class GameController {

    private final GameService gameService;
    private final CombatService combatService;

    public GameController(GameService gameService, CombatService combatService) {
        this.gameService = gameService;
        this.combatService = combatService;
    }
    public int getHeroHealth() {
        return gameService.getHero().getHealth();
    }

    public String getMonsterName() {
        return gameService.getCurrentMonster().getName();
    }

    public int getMonsterHealth() {
        return gameService.getCurrentMonster().getHealth();
    }
    public boolean isVictory() {
        return gameService.isVictory();
    }

    public boolean isDefeat() {
        return gameService.isDefeat();
    }

    public void quickAttack() {
        combatService.quickAttack(gameService.getCurrentMonster());

        gameService.checkMonsterDefeated();

        if (!gameService.isGameOver()) {
            combatService.monsterAttack(
                    gameService.getHero(),
                    gameService.getCurrentMonster()
            );
        }
    }
    public boolean powerfulAttack() {
        boolean hit = combatService.powerfulAttack(gameService.getCurrentMonster());

        gameService.checkMonsterDefeated();

        if (!gameService.isGameOver()) {
            combatService.monsterAttack(
                    gameService.getHero(),
                    gameService.getCurrentMonster()
            );
        }

        return hit;
    }
    public static GameController newGame(String heroName) {
        Hero hero = new Hero(heroName);

        List<Monster> monsters = List.of(
                new Monster("Goblin", 12, 2),
                new Monster("Orc", 18, 2),
                new Monster("Dragon", 25, 2)
        );

        GameService gameService = new GameService(hero, monsters);
        CombatService combatService = new CombatService();

        return new GameController(gameService, combatService);
    }
}

