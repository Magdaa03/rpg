package it.unicam.cs.mpgc.rpg125861.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HeroView extends ImageView {

    public HeroView() {
        Image heroSheet = new Image(
                getClass()
                        .getResource("/hero.png")
                        .toExternalForm()
        );

        setImage(heroSheet);
        setViewport(new Rectangle2D(34, 5, 15, 19));
        setFitWidth(75);
        setFitHeight(95);
        setSmooth(false);
    }
}
