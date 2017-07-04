package JavaFX;

import Checkers.Board;
import Checkers.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class FXBoard {

    private HashMap<Coordinate, FXCell> board = new HashMap<Coordinate, FXCell>();
    private List<Shape> alarmedShapes = new ArrayList<>();
    private List<Shape> lightedShapes = new ArrayList<>();

    FXBoard(Board board) {
        int size = board.getSize();
        for (int v = size - 1; v >= 0; v--) {
            for (int h = 0; h < size; h++) {
                Coordinate coordinate = new Coordinate(h, v);
                if (board.isPlaceable(coordinate)) {
                    FXCell fxCell = new FXCell(Color.BROWN);
                    this.board.put(coordinate, fxCell);
                    if (board.getChecker(coordinate) != null)
                        fxCell.fxChecker = new FXChecker(coordinate, board.getChecker(coordinate));
                } else this.board.put(coordinate, new FXCell(Color.WHITE));
            }
        }
    }

    class FXCell extends Rectangle {

        private FXChecker fxChecker;

        FXCell(Color color) {
            super(60, 60);
            super.setFill(color);
        }

        FXChecker getChecker() {
            return fxChecker;
        }
    }

    FXChecker getChecker(Coordinate coordinate) {
        return board.get(coordinate).fxChecker;
    }

    void removeChecker(Coordinate coordinate) {
        board.get(coordinate).fxChecker = null;
    }

    void setChecker(Coordinate coordinate, FXChecker checker) {
        checker.setCoordinate(coordinate);
        board.get(coordinate).fxChecker = checker;
    }

    void lightCell(Coordinate coordinate) {
        FXCell cell = board.get(coordinate);
        cell.setStrokeType(StrokeType.INSIDE);
        cell.setStroke(Color.YELLOW);
        cell.setStrokeWidth(2);
        lightedShapes.add(cell);
    }

    void deLight() {
        for (Shape shape: lightedShapes) {
            if (shape instanceof Rectangle) {
                shape.setStrokeWidth(0);
            }
        }
    }

    void alarmChecker(Coordinate coordinate) {
        FXChecker checker = board.get(coordinate).fxChecker;
        checker.setStyle("-fx-effect: dropshadow(one-pass-box, red, 9, 300, 0, 0);");
        alarmedShapes.add(checker);
    }

    void alarmCell(Coordinate coordinate) {
        FXCell cell = board.get(coordinate);
        cell.setStrokeType(StrokeType.INSIDE);
        cell.setStroke(Color.RED);
        cell.setStrokeWidth(2);
        alarmedShapes.add(cell);
    }

    void deAlarm() {
        for (Shape shape : alarmedShapes) {
            if(shape instanceof Circle) {
                shape.setStyle("");
            }
            if (shape instanceof Rectangle) {
                shape.setStrokeWidth(0);
            }
        }
    }

    HashMap<Coordinate, FXCell> getBoard() {
        return board;
    }
}
