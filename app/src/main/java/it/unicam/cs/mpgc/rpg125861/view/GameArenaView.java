package it.unicam.cs.mpgc.rpg125861.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GameArenaView extends HBox {

    private final MonsterView monsterView = new MonsterView();

    public GameArenaView() {
        setPrefHeight(280);
        setMinHeight(280);
        setMaxWidth(Double.MAX_VALUE);
        setAlignment(Pos.CENTER);
        setSpacing(250);

        HeroView heroView = new HeroView();
        getChildren().addAll(heroView, monsterView);
    }

    public void showMonster(String monsterName) {
        monsterView.showMonster(monsterName);
    }
}