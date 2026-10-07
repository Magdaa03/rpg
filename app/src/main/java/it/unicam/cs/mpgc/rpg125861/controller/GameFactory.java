package it.unicam.cs.mpgc.rpg125861.controller;

import it.unicam.cs.mpgc.rpg125861.entity.Hero;
import it.unicam.cs.mpgc.rpg125861.entity.Monster;
import it.unicam.cs.mpgc.rpg125861.service.CombatService;
import it.unicam.cs.mpgc.rpg125861.service.GameService;

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
}
