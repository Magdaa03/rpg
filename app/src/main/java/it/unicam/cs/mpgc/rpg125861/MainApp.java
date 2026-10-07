package it.unicam.cs.mpgc.rpg125861;

import it.unicam.cs.mpgc.rpg125861.controller.GameController;
import it.unicam.cs.mpgc.rpg125861.persistence.GameRepository;
import it.unicam.cs.mpgc.rpg125861.persistence.JsonGameRepository;
import it.unicam.cs.mpgc.rpg125861.controller.GameFactory;
import it.unicam.cs.mpgc.rpg125861.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        GameController gameController = GameFactory.newGame("Hero");
        GameRepository gameRepository = new JsonGameRepository();
        GameView gameView = new GameView(gameController, gameRepository);

        Scene scene = new Scene(gameView, 500, 400);

        stage.setTitle("Monster Hunter RPG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}