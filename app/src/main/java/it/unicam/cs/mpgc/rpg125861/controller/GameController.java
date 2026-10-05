package it.unicam.cs.mpgc.rpg125861.controller;

import it.unicam.cs.mpgc.rpg125861.service.CombatService;
import it.unicam.cs.mpgc.rpg125861.service.GameService;
import it.unicam.cs.mpgc.rpg125861.entity.Hero;
import it.unicam.cs.mpgc.rpg125861.entity.Monster;

import java.util.List;
import java.util.ArrayList;

public class GameController {

    private final GameService gameService;
    private final CombatService combatService;

    private final List<String> lastTurnMessages = new ArrayList<>();

    public GameController(GameService gameService, CombatService combatService) {
        this.gameService = gameService;
        this.combatService = combatService;
    }
    public List<String> getLastTurnMessages() {
        return List.copyOf(lastTurnMessages);
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
        lastTurnMessages.clear();

        if (gameService.isGameOver()) {
            return;
        }

        Monster attackedMonster = gameService.getCurrentMonster();
        combatService.quickAttack(attackedMonster);
        lastTurnMessages.add("Quick attack: 5 damage.");

        gameService.checkMonsterDefeated();
        finishMonsterTurn(attackedMonster);
    }

    public boolean powerfulAttack() {
        lastTurnMessages.clear();

        if (gameService.isGameOver()) {
            return false;
        }

        Monster attackedMonster = gameService.getCurrentMonster();
        boolean hit = combatService.powerfulAttack(attackedMonster);

        if (hit) {
            lastTurnMessages.add("Powerful attack hit: 10 damage.");
        } else {
            lastTurnMessages.add("Powerful attack missed.");
        }

        gameService.checkMonsterDefeated();
        finishMonsterTurn(attackedMonster);

        return hit;
    }

    private void finishMonsterTurn(Monster attackedMonster) {
        if (attackedMonster.getHealth() <= 0) {
            lastTurnMessages.add(attackedMonster.getName() + " defeated.");

            if (gameService.isVictory()) {
                lastTurnMessages.add("Victory! You defeated all monsters.");
            } else {
                lastTurnMessages.add(
                        "Next monster: "
                                + gameService.getCurrentMonster().getName()
                );
            }

            return;
        }

        int heroHealthBeforeAttack = gameService.getHero().getHealth();
        combatService.monsterAttack(gameService.getHero(), attackedMonster);

        int damageTaken = heroHealthBeforeAttack
                - gameService.getHero().getHealth();

        lastTurnMessages.add(
                attackedMonster.getName()
                        + " counterattacks for "
                        + damageTaken
                        + " damage."
        );

        if (gameService.isDefeat()) {
            lastTurnMessages.add("Defeat: the hero has no health left.");
        }
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

