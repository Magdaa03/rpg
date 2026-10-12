package it.unicam.cs.mpgc.rpg125861.view;

import javafx.scene.layout.Pane;

public class GameArenaView extends Pane {

    public GameArenaView() {
        setPrefHeight(420);
        setMinHeight(200);

        HeroView heroView = new HeroView();
        heroView.setLayoutX(100);
        heroView.setLayoutY(220);

        getChildren().add(heroView);
    }
}