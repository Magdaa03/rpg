package it.unicam.cs.mpgc.rpg125861.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonsterView extends ImageView {

    private String displayedMonsterName;

    public void showMonster(String monsterName) {
        if (monsterName.equals(displayedMonsterName)) {
            return;
        }

        if ("Goblin".equals(monsterName)) {
            setMonsterImage("/goblin.gif", 100);
        } else if ("Orc".equals(monsterName)) {
            setMonsterImage("/orc.png", 110);
        } else if ("Dragon".equals(monsterName)) {
            setMonsterImage("/dragon.gif", 140);
        } else {
            throw new IllegalArgumentException(
                    "Unknown monster: " + monsterName
            );
        }

        displayedMonsterName = monsterName;
    }

    private void setMonsterImage(String resourcePath, double size) {
        Image monsterImage = new Image(
                getClass().getResource(resourcePath).toExternalForm()
        );

        setImage(monsterImage);
        setFitWidth(size);
        setFitHeight(size);
        
        setPreserveRatio(true);
        setSmooth(false);
    }
}
