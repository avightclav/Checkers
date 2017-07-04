package Checkers;

import java.util.ArrayList;

public class MovementCalculator {
    private int size;
    private Color downsideColor;
    private Board board;

    MovementCalculator(Color pColor, Board board) {
        this.downsideColor = pColor;
        this.board = board;
        this.size = board.getSize();
    }

    public static class StrokeMove {
        private Coordinate strokingChecker;
        private Coordinate checkerToDestroy;
        private Coordinate newCoordinate;

        StrokeMove(Coordinate strokingChecker, Coordinate checkerToDestroy, Coordinate newCoordinate) {
            this.strokingChecker = strokingChecker;
            this.checkerToDestroy = checkerToDestroy;
            this.newCoordinate = newCoordinate;
        }

        public Coordinate getStrokingChecker() {
            return strokingChecker;
        }

        public Coordinate getCheckerToDestroy() {
            return checkerToDestroy;
        }

        public Coordinate getNewCoordinate() {
            return newCoordinate;
        }
    }

    ArrayList<Coordinate> checkLeftUpDiag(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMovements = new ArrayList<>();
        int i = 1;
        Coordinate diagCoordinate = getOnLeftSide(coordinate, i);
        while (onBoard(diagCoordinate) && isQueenMovingPossible(coordinate, diagCoordinate)) {
            possibleMovements.add(diagCoordinate);
            diagCoordinate = getOnLeftSide(coordinate, i);
            i++;
        }
        return possibleMovements;
    }

    ArrayList<Coordinate> checkLeftDownDiag(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMovements = new ArrayList<>();
        int i = -1;
        Coordinate diagCoordinate = getOnRightSide(coordinate, i);
        while (onBoard(diagCoordinate) && isQueenMovingPossible(coordinate, diagCoordinate)) {
            possibleMovements.add(diagCoordinate);
            diagCoordinate = getOnRightSide(coordinate, i);
            i--;
        }
        return possibleMovements;
    }

    ArrayList<Coordinate> checkRightUpDiag(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMovements = new ArrayList<>();
        int i = 1;
        Coordinate diagCoordinate = getOnRightSide(coordinate, i);
        while (onBoard(diagCoordinate) && isQueenMovingPossible(coordinate, diagCoordinate)) {
            possibleMovements.add(diagCoordinate);
            diagCoordinate = getOnRightSide(coordinate, i);
            i++;
        }
        return possibleMovements;
    }

    ArrayList<Coordinate> checkRightDownDiag(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMovements = new ArrayList<>();
        int i = -1;
        Coordinate diagCoordinate = getOnLeftSide(coordinate, i);
        while (onBoard(diagCoordinate) && isQueenMovingPossible(coordinate, diagCoordinate)) {
            possibleMovements.add(diagCoordinate);
            diagCoordinate = getOnLeftSide(coordinate, i);
            i--;
        }
        return possibleMovements;
    }


    ArrayList<Coordinate> getPossibleMovements(Coordinate coordinate) {
        ArrayList<Coordinate> possibleMovements = new ArrayList<>();
        if (board.getChecker(coordinate).isQueen()) {
            possibleMovements.addAll(checkLeftUpDiag(coordinate));
            possibleMovements.addAll(checkRightDownDiag(coordinate));
            possibleMovements.addAll(checkRightUpDiag(coordinate));
            possibleMovements.addAll(checkLeftDownDiag(coordinate));
        } else {
            Coordinate lCoordinate = getLeftUp(coordinate);
            Coordinate rCoordinate = getRightUp(coordinate);
            if (checkOccupied(lCoordinate))
                possibleMovements.add(lCoordinate);
            if (checkOccupied(rCoordinate))
                possibleMovements.add(rCoordinate);
        }
        return possibleMovements;
    }

    private Coordinate getOnLeftSide(Coordinate from, int to) {
        Coordinate lCoordinate;
        if (downsideColor == board.getChecker(from).getColor())
            lCoordinate = new Coordinate(from.getHCoordinate() - to, from.getVCoordinate() + to);
        else
            lCoordinate = new Coordinate(from.getHCoordinate() + to, from.getVCoordinate() - to);
        if (onBoard(lCoordinate))
            return lCoordinate;
        else return null;
    }

    private Coordinate getOnRightSide(Coordinate from, int to) {
        Coordinate rCoordinate;
        if (downsideColor == board.getChecker(from).getColor())
            rCoordinate = new Coordinate(from.getHCoordinate() + to, from.getVCoordinate() + to);
        else
            rCoordinate = new Coordinate(from.getHCoordinate() - to, from.getVCoordinate() - to);
        if (onBoard(rCoordinate))
            return rCoordinate;
        else return null;
    }

    Coordinate getLeftUp(Coordinate coordinate) {
        return getOnLeftSide(coordinate, 1);
    }

    Coordinate getDoubledLeftUp(Coordinate coordinate) {
        return getOnLeftSide(coordinate, 2);
    }

    Coordinate getLeftDown(Coordinate coordinate) {
        return getOnLeftSide(coordinate, -1);
    }

    Coordinate getDoubledLeftDown(Coordinate coordinate) {
        return getOnLeftSide(coordinate, -2);
    }

    Coordinate getRightUp(Coordinate coordinate) {
        return getOnRightSide(coordinate, 1);
    }

    Coordinate getDoubledRightUp(Coordinate coordinate) {
        return getOnRightSide(coordinate, 2);
    }

    Coordinate getRightDown(Coordinate coordinate) {
        return getOnRightSide(coordinate, -1);
    }

    Coordinate getDoubledRightDown(Coordinate coordinate) {
        return getOnRightSide(coordinate, -2);
    }

    private boolean isQueenMovingPossible(Coordinate queen, Coordinate to) {
        return ((queen != null) && (board.getChecker(queen) != null) && (board.getChecker(queen).isQueen())
                && ((board.getChecker(to) == null) || (board.getChecker(queen).getColor() != board.getChecker(to).getColor())));
    }

    private boolean checkOccupied(Coordinate coordinate) {
        return coordinate != null && (board.getChecker(coordinate) == null);
    }

    private boolean onBoard(Coordinate coordinate) {
        if (coordinate == null)
            return false;
        int hCoordinate = coordinate.getHCoordinate();
        int vCoordinate = coordinate.getVCoordinate();
        return ((hCoordinate < size) && (hCoordinate >= 0) &&
                (vCoordinate < size) && (vCoordinate >= 0));
    }
}
