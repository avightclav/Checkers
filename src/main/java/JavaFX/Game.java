package JavaFX;

import Checkers.Board;
import Checkers.Playboard;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game {
    static Stage menuStage = new Stage();
    static Scene menuScene;

    static public void start() {
        menuStage.setMaximized(false);
        menuStage.setMaximized(true);
        menuStage.setTitle("Checkers 1.0 Alpha");
        menuStage.minHeightProperty().setValue(500);
        menuStage.minWidthProperty().setValue(500);

        BorderPane menuPane = new BorderPane();
        menuPane.backgroundProperty().setValue(new Background(new BackgroundFill(Color.BURLYWOOD, null, null)));

        HBox labelsPane = new HBox();
        labelsPane.setAlignment(Pos.CENTER);
        Label checkers = new Label("Checkers");
        checkers.setTranslateY(50);
        checkers.setTextFill(Color.BLACK);
        checkers.setStyle("-fx-color: white;" +
                "-fx-font-size: 60;");
        Label playerColorLabel = new Label();
        StringProperty pclText = playerColorLabel.textProperty();
        labelsPane.getChildren().addAll(checkers, playerColorLabel);

        Pane russianCheckersButton = new Pane();
        Label l1 = new Label("Russian Checkers");
        l1.setStyle("-fx-font-family: Gill Sans;" +
                "-fx-font-size: 30");
        l1.setLayoutX(50);
        l1.setLayoutY(20);
        Circle r1white = new Circle(24, Color.WHITE);
        r1white.setLayoutX(100);
        r1white.setLayoutY(45);
        r1white.setOnMouseEntered(event -> r1white.toFront());
        r1white.setOnMouseClicked(event -> startGame(8, Checkers.Color.WHITE));
        r1white.setOnMouseEntered(event -> r1white.setStyle("-fx-effect: dropshadow(one-pass-box, gray, 5, -6, 0, 0);"));
        r1white.setOnMouseExited(event -> r1white.setStyle(""));
        Circle r1black = new Circle(24, Color.BLACK);
        r1black.setLayoutX(230);
        r1black.setLayoutY(45);
        r1black.setOnMouseEntered(event -> r1white.toFront());
        r1black.setOnMouseClicked(event -> startGame(8, Checkers.Color.BLACK));
        r1black.setOnMouseEntered(event -> r1black.setStyle("-fx-effect: dropshadow(one-pass-box, gray, 5, 6, 0, 0);"));
        r1black.setOnMouseExited(event -> r1black.setStyle(""));
        Rectangle r1 = new Rectangle(330, 90 , Color.CYAN);
        russianCheckersButton.getChildren().addAll(r1white, r1black, r1, l1);
        russianCheckersButton.layoutYProperty().bind(menuPane.heightProperty().divide(2).add(-65));
        russianCheckersButton.layoutXProperty().bind(menuPane.widthProperty().divide(2).add(-165));
        russianCheckersButton.setOnMouseEntered(event -> {
            l1.toBack();
            r1white.toFront();
            r1black.toFront();
        });
        russianCheckersButton.setOnMouseExited(event -> {
            l1.toFront();
            r1white.toBack();
            r1black.toBack();
        });

        Pane worldWideCheckers = new Pane();
        Label l2 = new Label("Worldwide Checkers");
        l2.setStyle("-fx-font-family: Gill Sans;" +
                "-fx-font-size: 30");
        l2.setLayoutX(30);
        l2.setLayoutY(20);
        Rectangle r2 = new Rectangle(330, 90 , Color.CYAN);
        Circle r2white = new Circle(24, Color.WHITE);
        r2white.setLayoutX(100);
        r2white.setLayoutY(45);
        r2white.setOnMouseEntered(event -> r2white.toFront());
        r2white.setOnMouseClicked(event -> startGame(10, Checkers.Color.WHITE));
        r2white.setOnMouseEntered(event -> r2white.setStyle("-fx-effect: dropshadow(one-pass-box, gray, 5, -6, 0, 0);"));
        r2white.setOnMouseExited(event -> r2white.setStyle(""));
        Circle r2black = new Circle(24, Color.BLACK);
        r2black.setLayoutX(230);
        r2black.setLayoutY(45);
        r2black.setOnMouseEntered(event -> r2white.toFront());
        r2black.setOnMouseClicked(event -> startGame(10, Checkers.Color.BLACK));
        r2black.setOnMouseEntered(event -> r2black.setStyle("-fx-effect: dropshadow(one-pass-box, gray, 5, 6, 0, 0);"));
        r2black.setOnMouseExited(event -> r2black.setStyle(""));
        worldWideCheckers.getChildren().addAll(r2black, r2white, r2, l2);
        worldWideCheckers.layoutXProperty().bind(menuPane.widthProperty().divide(2).add(-165));
        worldWideCheckers.layoutYProperty().bind(menuPane.heightProperty().divide(2).add(65));
        worldWideCheckers.setOnMouseEntered(event -> {
            l2.toBack();
            r2white.toFront();
            r2black.toFront();
        });
        worldWideCheckers.setOnMouseExited(event -> {
            l2.toFront();
            r2white.toBack();
            r2black.toBack();
        });

        menuPane.setAlignment(labelsPane, Pos.CENTER);
        menuPane.setTop(labelsPane);
        menuPane.getChildren().add(russianCheckersButton);
        menuPane.getChildren().add(worldWideCheckers);

        menuScene = new Scene(menuPane);
        menuStage.setScene(menuScene);
        menuStage.show();
    }

    static void startGame(int size, Checkers.Color color) {
        Board b = new Board(size);
        Playboard pb = new Playboard(b, color);
        Gameboard gb = new Gameboard(pb, b);
        GridPane gp = (GridPane) gb.renderBoard();
        gp.setAlignment(Pos.CENTER);
        menuScene = new Scene(gp);
        menuStage.setScene(menuScene);
        menuStage.setMaximized(false);
        menuStage.setMaximized(true);
    }

    static void end(int i) {



        Label l = new Label();
        l.setStyle("-fx-font-size: 50");
        l.setTextFill(Color.RED);

        switch (i) {
            case(1): {
                l.textProperty().setValue("WHITE WINS");
            }
            case(2): {
                l.textProperty().setValue("BLACK WINS");
            }
        }
        BorderPane endPane = new BorderPane(l);
        Scene endScene = new Scene(endPane);
        endPane.setOnMouseClicked(event -> Game.start());
        menuStage.setScene(endScene);
        menuStage.show();
    }
}
