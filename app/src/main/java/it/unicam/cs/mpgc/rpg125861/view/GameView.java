package it.unicam.cs.mpgc.rpg125861.view;

import it.unicam.cs.mpgc.rpg125861.controller.GameController;
import it.unicam.cs.mpgc.rpg125861.persistence.GameRepository;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameView extends VBox {

    private final GameController gameController;
    private final GameRepository gameRepository;

    private final Label heroLabel = new Label();
    private final Label monsterLabel = new Label();
    private final Label messageLabel = new Label();
    private final TextArea logArea = new TextArea();

    public GameView(
            GameController gameController,
            GameRepository gameRepository
    ) {
        this.gameController = gameController;
        this.gameRepository = gameRepository;

        configureLogArea();
        configureLayout();
        updateLabels();
    }

    private void configureLogArea() {
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefRowCount(8);
    }

    private void configureLayout() {
        Button saveButton = new Button("Save Game");
        Button quickAttackButton = new Button("Quick Attack");
        Button powerfulAttackButton = new Button("Powerful Attack");

        saveButton.setOnAction(event -> saveGame());
        quickAttackButton.setOnAction(event -> handleQuickAttack());
        powerfulAttackButton.setOnAction(event -> handlePowerfulAttack());

        setSpacing(15);
        getChildren().addAll(
                heroLabel,
                monsterLabel,
                messageLabel,
                logArea,
                saveButton,
                quickAttackButton,
                powerfulAttackButton
        );
    }

    private void saveGame() {
        try {
            gameRepository.save(gameController.createSaveData());
            messageLabel.setText("Game saved.");
            logArea.appendText("Game saved." + System.lineSeparator());
        } catch (IOException exception) {
            messageLabel.setText("Unable to save the game.");
            logArea.appendText(
                    "Error while saving the game."
                            + System.lineSeparator()
            );
        }
    }

    private void handleQuickAttack() {
        gameController.quickAttack();
        showActionResult("Quick Attack: HIT!");
        showTurnMessages();
        updateLabels();
    }

    private void handlePowerfulAttack() {
        boolean hit = gameController.powerfulAttack();

        if (hit) {
            showActionResult("Powerful Attack: HIT!");
        } else {
            showActionResult("Powerful Attack: MISS!");
        }

        showTurnMessages();
        updateLabels();
    }

    private void showActionResult(String attackMessage) {
        if (gameController.isVictory()) {
            messageLabel.setText("You defeated all monsters!");
        } else if (gameController.isDefeat()) {
            messageLabel.setText("The hero has been defeated.");
        } else {
            messageLabel.setText(attackMessage);
        }
    }

    private void showTurnMessages() {
        for (String message : gameController.getLastTurnMessages()) {
            logArea.appendText(message + System.lineSeparator());
        }
    }

    private void updateLabels() {
        heroLabel.setText(
                "Hero HP: " + gameController.getHeroHealth()
        );

        if (gameController.isVictory()) {
            monsterLabel.setText("Victory!");
        } else if (gameController.isDefeat()) {
            monsterLabel.setText("Defeat!");
        } else {
            monsterLabel.setText(
                    "Monster: " + gameController.getMonsterName()
                            + " - HP: " + gameController.getMonsterHealth()
            );
        }
    }
}