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
//        BorderPane root = new BorderPane();
//        Board board = new Board(8);
//        Playboard playboard = new Playboard(board, Color.WHITE, 8);
//        Gameboard gameboard = new Gameboard(playboard, board);
//        Scene scene = new Scene(gameboard.renderBoard());
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        gameboard.setCurrent(new Coordinate(0, 4));
//        gameboard.move(new Coordinate(2, 2));
//        gameboard.setCurrent(new Coordinate(0,0));
//        gameboard.move(new Coordinate(3, 3));
//        gameboard.setCurrent(new Coordinate(2, 2));
//        gameboard.move(new Coordinate(4, 4));



  //      gameboard.move(new Coordinate(1, 3));
//        gameboard.setCurrent(new Coordinate(5, 5));
//        gameboard.move(new Coordinate(4, 4));
//        gameboard.setCurrent(new Coordinate(0, 0));
//        gameboard.setCurrent(new Coordinate(2, 2));
//        gameboard.move(new Coordinate(3, 3));
//        gameboard.setCurrent(new Coordinate(1, 5));
//        gameboard.move(new Coordinate(2, 4));
//        gameboard.setCurrent(new Coordinate(3, 3));
//        gameboard.move(new Coordinate(1, 5));
//        gameboard.setCurrent(new Coordinate(0, 6));
//        gameboard.move(new Coordinate(2, 4));
//        gameboard.setCurrent(new Coordinate(1, 1));
//        gameboard.move(new Coordinate(2, 2));
//        gameboard.setCurrent(new Coordinate(2, 4));
//        gameboard.move(new Coordinate(3, 3));
//        gameboard.setCurrent(new Coordinate(4, 2));
//        gameboard.move(new Coordinate(0, 4));
//        gameboard.setCurrent(new Coordinate(0, 6));
//        gameboard.move(new Coordinate(2, 4));


    }

    public static void main(String[] args) {
        launch(args);
    }

}
