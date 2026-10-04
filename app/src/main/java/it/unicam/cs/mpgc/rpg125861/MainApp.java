package it.unicam.cs.mpgc.rpg125861;

import it.unicam.cs.mpgc.rpg125861.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        GameController gameController = GameController.newGame("Hero");

        Label heroLabel = new Label(
                "Hero HP: " + gameController.getHeroHealth()
        );

        Label monsterLabel = new Label(
                "Monster: " + gameController.getMonsterName()
                        + " - HP: " + gameController.getMonsterHealth()
        );

        Label messageLabel = new Label();

        Button quickAttackButton = new Button("Quick Attack");
        Button powerfulAttackButton = new Button("Powerful Attack");

        quickAttackButton.setOnAction(event -> {

            gameController.quickAttack();

            if (gameController.isVictory()) {
                messageLabel.setText("You defeated all monsters!");
            } else if (gameController.isDefeat()) {
                messageLabel.setText("The hero has been defeated.");
            } else {
                messageLabel.setText("Quick Attack: HIT!");
            }

            updateLabels(
                    gameController,
                    heroLabel,
                    monsterLabel
            );
        });

        powerfulAttackButton.setOnAction(event -> {

            boolean hit = gameController.powerfulAttack();

            if (gameController.isVictory()) {
                messageLabel.setText("You defeated all monsters!");
            } else if (gameController.isDefeat()) {
                messageLabel.setText("The hero has been defeated.");
            } else if (hit) {
                messageLabel.setText("Powerful Attack: HIT!");
            } else {
                messageLabel.setText("Powerful Attack: MISS!");
            }

            updateLabels(
                    gameController,
                    heroLabel,
                    monsterLabel
            );
        });

        VBox root = new VBox(
                15,
                heroLabel,
                monsterLabel,
                messageLabel,
                quickAttackButton,
                powerfulAttackButton
        );

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Monster Hunter RPG");
        stage.setScene(scene);
        stage.show();
    }

    private void updateLabels(
            GameController gameController,
            Label heroLabel,
            Label monsterLabel
    ) {
        heroLabel.setText(
                "Hero HP: " + gameController.getHeroHealth()
        );

        if (gameController.isVictory()) {
            monsterLabel.setText("Victory!");
            return;
        }

        if (gameController.isDefeat()) {
            monsterLabel.setText("Defeat!");
            return;
        }

        monsterLabel.setText(
                "Monster: " + gameController.getMonsterName()
                        + " - HP: " + gameController.getMonsterHealth()
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
