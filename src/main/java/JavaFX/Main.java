package JavaFX;

import Checkers.Board;
import Checkers.Color;
import Checkers.Coordinate;
import Checkers.Playboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
