package it.unicam.cs.mpgc.rpg125861.view;

import it.unicam.cs.mpgc.rpg125861.controller.GameController;
import it.unicam.cs.mpgc.rpg125861.persistence.GameRepository;
import it.unicam.cs.mpgc.rpg125861.controller.GameFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class GameView extends VBox {

    private GameController gameController;
    private final GameRepository gameRepository;


    private final Label messageLabel = new Label();
    private final Label logArea = new Label();
    private final GameArenaView arenaView = new GameArenaView();
    private final BattleStatusView battleStatusView = new BattleStatusView();

    public GameView(
            GameController gameController,
            GameRepository gameRepository
    ) {
        this.gameController = gameController;
        this.gameRepository = gameRepository;

        configureLogArea();
        configureBackground();
        configureLayout();
        updateLabels();
    }

    private void configureLogArea() {
        logArea.setWrapText(true);
        logArea.setMaxWidth(600);
        logArea.setStyle("-fx-text-fill: white;");
        logArea.setMinHeight(80);
        logArea.setPrefHeight(80);
        logArea.setMaxHeight(80);
    }
    private void appendLog(String message) {
        logArea.setText(
                logArea.getText() + message + System.lineSeparator()
        );
    }

    private void configureLayout() {
        Button saveButton = new Button("Save Game");
        Button quickAttackButton = new Button("Quick Attack");
        Button powerfulAttackButton = new Button("Powerful Attack");
        Button loadButton = new Button("Load Game");

        saveButton.setOnAction(event -> saveGame());
        quickAttackButton.setOnAction(event -> handleQuickAttack());
        powerfulAttackButton.setOnAction(event -> handlePowerfulAttack());
        loadButton.setOnAction(event -> loadGame());

        setSpacing(15);
        setAlignment(Pos.TOP_CENTER);
        HBox logContainer = new HBox(logArea);
        logContainer.setAlignment(Pos.CENTER_LEFT);
        logContainer.setMaxWidth(Double.MAX_VALUE);



        getChildren().addAll(
                battleStatusView,
                messageLabel,
                arenaView,
                logContainer,
                saveButton,
                loadButton,
                quickAttackButton,
                powerfulAttackButton
        );

    }

    private void saveGame() {
        try {
            gameRepository.save(gameController.createSaveData());
            messageLabel.setText("Game saved.");
            appendLog("Game saved.");
        } catch (IOException exception) {
            messageLabel.setText("Unable to save the game.");
            appendLog("Error while saving the game.");
        }
    }
    private void loadGame() {
        if (!gameRepository.hasSave()) {
            messageLabel.setText("No saved game found.");
            return;
        }

        try {
            gameController = GameFactory.loadGame(gameRepository.load());
            messageLabel.setText("Game loaded.");
            logArea.setText("");
            appendLog("Game loaded.");
            updateLabels();
        } catch (IOException | IllegalArgumentException exception) {
            messageLabel.setText("Unable to load the game.");
            appendLog("Error while loading the game.");
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
            appendLog(message);
        }
    }

    private void updateLabels() {
        battleStatusView.update(gameController);

        if (!gameController.isVictory() && !gameController.isDefeat()) {
            arenaView.showMonster(gameController.getMonsterName());
        }
    }

    private void configureBackground() {
        Image image = new Image(
                getClass().getResource("/battleback1.png").toExternalForm()
        );

        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );

        setBackground(new Background(backgroundImage));
    }
}