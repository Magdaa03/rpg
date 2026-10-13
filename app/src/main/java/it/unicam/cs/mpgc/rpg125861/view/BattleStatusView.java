package it.unicam.cs.mpgc.rpg125861.view;

import it.unicam.cs.mpgc.rpg125861.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class BattleStatusView extends VBox {

    private final Label heroLabel = new Label();
    private final Label monsterLabel = new Label();
    private final ProgressBar heroHealthBar = new ProgressBar();
    private final ProgressBar monsterHealthBar = new ProgressBar();

    public BattleStatusView() {
        configureLabels();
        configureLayout();
    }

    public void update(GameController gameController) {
        int heroHealth = gameController.getHeroHealth();

        heroLabel.setText("Hero HP: " + heroHealth);
        heroHealthBar.setProgress(
                (double) heroHealth / gameController.getHeroMaxHealth()
        );

        if (gameController.isVictory()) {
            monsterLabel.setText("Victory!");
            monsterHealthBar.setProgress(0);
            return;
        }

        if (gameController.isDefeat()) {
            monsterLabel.setText("Defeat!");
        } else {
            monsterLabel.setText(
                    "Monster: " + gameController.getMonsterName()
                            + " - HP: " + gameController.getMonsterHealth()
            );
        }

        monsterHealthBar.setProgress(
                (double) gameController.getMonsterHealth()
                        / gameController.getMonsterMaxHealth()
        );
    }

    private void configureLabels() {
        String labelStyle =
                "-fx-text-fill: white;"
                        + "-fx-background-color: rgba(0, 0, 0, 0.55);"
                        + "-fx-padding: 6 10 6 10;";

        heroLabel.setStyle(labelStyle);
        monsterLabel.setStyle(labelStyle);
    }

    private void configureLayout() {
        heroHealthBar.setPrefWidth(250);
        monsterHealthBar.setPrefWidth(250);

        setSpacing(15);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(
                heroLabel,
                heroHealthBar,
                monsterLabel,
                monsterHealthBar
        );
    }
}
