package JavaFX;

import Checkers.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.HPos;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.List;

class Gameboard {
    private Playboard playboard;
    private Board board;
    private GridPane gameboardPane;
    private FXChecker currentChecker;
    private FXBoard fxBoard;

    Gameboard(Playboard playboard, Board board) {
        this.playboard = playboard;
        this.board = board;
        this.gameboardPane = new GridPane(playboard.getSize());
    }

    Region renderBoard() {
        fxBoard = new FXBoard(board);
        ReadOnlyDoubleProperty playboardGridWidth = gameboardPane.widthProperty();
        ReadOnlyDoubleProperty playboardGridHeight = gameboardPane.heightProperty();
        NumberBinding cellProperty = Bindings.min(playboardGridWidth, playboardGridHeight).divide(playboard.getSize());


        for (HashMap.Entry<Coordinate, FXBoard.FXCell> entry : fxBoard.getBoard().entrySet()) {
            Coordinate coordinate = entry.getKey();
            FXBoard.FXCell cell = entry.getValue();
            FXChecker checker = cell.getChecker(coordinate);
            cell.setOnMouseClicked(c -> {
                if (currentChecker != null) {
                    move(coordinate);
                }
            });
            gameboardPane.add(cell, coordinate.getHCoordinate(), coordinate.getVCoordinate());
            if (checker != null) {
                gameboardPane.add(checker, coordinate.getHCoordinate(), coordinate.getVCoordinate());
                gameboardPane.setHalignment(checker, HPos.CENTER);
                checker.setOnMouseClicked(c -> {
                    this.setCurrent(checker.getCoordinate());
                    System.out.println(playboard.getChecker(checker.getCoordinate()).isQueen());
                });
            }
            cell.widthProperty().bind(cellProperty);
            cell.heightProperty().bind(cellProperty);
        }
        gameboardPane.minWidthProperty().setValue(400);
        gameboardPane.minHeightProperty().setValue(400);
        return gameboardPane;
    }

    void setCurrent(Coordinate coordinate) {
        if (playboard.setCurrent(coordinate) == 1) {
            fxBoard.deLight();
            if (currentChecker != null)
                currentChecker.unlight();
            currentChecker = fxBoard.getChecker(coordinate);
            fxBoard.getChecker(coordinate).light();

            if (playboard.getStrokeMoves().isEmpty()) {
                for (Coordinate cellToLight : playboard.getPossibleCurrentMoves()) {
                    fxBoard.lightCell(cellToLight);
                }
            }
        }
    }

    private void move(Coordinate coordinate) {
        List<MovementCalculator.StrokeMove> strokeMoves = playboard.getStrokeMoves();
        FXChecker checkerToDestroy = null;
        if (!(strokeMoves.isEmpty())) {
            for (MovementCalculator.StrokeMove sm : strokeMoves) {
                if ((sm.getStrokingChecker().equals(currentChecker.getCoordinate())) && (sm.getNewCoordinate().equals(coordinate))) {
                    checkerToDestroy = fxBoard.getChecker(sm.getCheckerToDestroy());
                    break;
                }
            }
        }
        if (playboard.safetyMove(coordinate) == 1) {
            fxBoard.deAlarm();
            fxBoard.deLight();

            gameboardPane.getChildren().remove(checkerToDestroy);
            gameboardPane.getChildren().remove(currentChecker);
            currentChecker.setCoordinate(coordinate);
            fxBoard.setChecker(coordinate, currentChecker);
            gameboardPane.add(currentChecker, coordinate.getHCoordinate(), coordinate.getVCoordinate());
            currentChecker.unlight();

            if (playboard.getCurrentChecker() != null)
                setCurrent(playboard.getCurrentChecker());
            else
                currentChecker = null;

            for (MovementCalculator.StrokeMove sm : playboard.getStrokeMoves()) {
                Coordinate coordinateToAlarmChecker = sm.getCheckerToDestroy();
                Coordinate coordinateAlarmCell = sm.getNewCoordinate();
                fxBoard.alarmChecker(coordinateToAlarmChecker);
                fxBoard.alarmCell(coordinateAlarmCell);
            }
        }
    }
}


