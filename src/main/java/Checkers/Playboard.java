package Checkers;

import java.util.ArrayList;
import java.util.List;

public class Playboard {
    private Board board;
    private Color pColor;
    private Color currentColor = Color.WHITE;

    private Coordinate currentChecker;
    private MovementCalculator movement;

    public List<Coordinate> getPossibleCurrentMoves() {
        return possibleCurrentMoves;
    }

    private List<Coordinate> possibleCurrentMoves;
    private List<MovementCalculator.StrokeMove> strokeMoves = new ArrayList<>();
    private int size;

    public Playboard(Board board, Color pColor) {
        this.board = board;
        this.pColor = pColor;
        this.movement = new MovementCalculator(pColor, board);
        this.size = board.getSize();
        int lines = (size / 2) - 1;

        for (int v = size - 1; v > size - lines - 1; v--) {
            for (int h = 0; h < size; h++) {
                Coordinate coordinate = new Coordinate(h, v);
                if (board.isPlaceable(coordinate))
                    board.setCell(coordinate, new Checker(pColor.getOpposite()));
            }
        }

        for (int v = 0; v < lines; v++) {
            for (int h = 0; h < size; h++) {
                Coordinate coordinate = new Coordinate(h, v);
                if (board.isPlaceable(coordinate))
                    board.setCell(coordinate, new Checker(pColor));
            }
        }
    }

    public Playboard(Board board, Color pColor, int crazymegahelltestboard) {
        this.board = board;
        this.pColor = pColor;
        this.movement = new MovementCalculator(pColor, board);
        this.size = board.getSize();
        board.setCell(new Coordinate(0, 4), new Checker(Color.WHITE));
        board.getChecker(new Coordinate(0, 4)).setQueen();
//      board.setCell(new Coordinate(0, 2), new Checker(Color.WHITE));
        board.setCell(new Coordinate(0, 0), new Checker(Color.BLACK));
    }

    public int setCurrent(Coordinate coordinate) {
        if (strokeMoves.isEmpty()) {
            Checker checker = board.getChecker(coordinate);
            if (checker != null) {
                if (checker.getColor() == currentColor) {
                    currentChecker = coordinate;
                    possibleCurrentMoves = movement.getPossibleMovements(coordinate);
                    return 1;
                }
            }
            return 0;
        } else {
            for (MovementCalculator.StrokeMove sm : strokeMoves) {
                if (sm.getStrokingChecker().equals(coordinate)) {
                    currentChecker = coordinate;
                    return 1;
                }
            }
        }
        return 0;
    }

    public Coordinate getCurrentChecker() {
        return currentChecker;
    }

    private void swapCurrentColor() {
        this.currentColor = currentColor.getOpposite();
        this.currentChecker = null;
        this.strokeMoves.clear();
    }

    public int safetyMove(Coordinate coordinate) {
        if (strokeMoves.isEmpty()) {
            if (possibleCurrentMoves.contains(coordinate)) {
                move(coordinate);
                this.checkField();
                return 1;
            } else
                return 0;
        } else {
            for (MovementCalculator.StrokeMove sm : strokeMoves) {
                if ((sm.getStrokingChecker().equals(currentChecker)) && (sm.getNewCoordinate().equals(coordinate))) {
                    if (move(coordinate) == 1) {
                        board.removeChecker(sm.getCheckerToDestroy());
                        this.checkField(coordinate);
                        return 1;
                    } else return 0;
                }
            }
        }
        return 0;
    }

    private int move(Coordinate coordinate) {
        if ((board.getChecker(coordinate) == null) && (currentChecker != null)) {
            Checker checker = board.getChecker(currentChecker);
            board.removeChecker(currentChecker);
            board.setCell(checker, coordinate);
            return 1;
        }
        return 0;
    }

    private void checkField() {
        strokeMoves.clear();
        swapCurrentColor();
        addStrokeMovements();
        checkBorders();
    }

    private void checkField(Coordinate lastMove) {
        strokeMoves.clear();
        List<MovementCalculator.StrokeMove> strokeMoves = getStrokeMovements(lastMove);
        if (!(strokeMoves.isEmpty())) {
            setCurrent(lastMove);
            this.strokeMoves.addAll(strokeMoves);
            checkBorders();
        } else {
            checkField();
        }
    }

    private void checkBorders() {
        int bottom = 0;
        int top = size - 1;


        for (int h = 0; h < size; h++) {
            Coordinate topCoordinate = new Coordinate(h, top);
            Checker checker1 = getChecker(topCoordinate);
            if ((checker1 != null) && (checker1.getColor() == pColor)) {
                checker1.setQueen();
            }

            Coordinate bottomCoordinate = new Coordinate(h, bottom);
            Checker checker2 = getChecker(bottomCoordinate);
            if ((checker2 != null) && (checker2.getColor() == pColor.getOpposite())) {
                checker2.setQueen();
            }

        }
    }

    private List<MovementCalculator.StrokeMove> getStrokeMovements(Coordinate reviseCheckerCoordinate) {
        List<MovementCalculator.StrokeMove> strokeMoves = new ArrayList<>();
        Coordinate checkerToDestroyCoordinate = movement.getRightUp(reviseCheckerCoordinate);
        Checker checkerToDestroy = (checkerToDestroyCoordinate != null) ? board.getChecker(checkerToDestroyCoordinate) : null;
        if ((checkerToDestroy != null) && (checkerToDestroy.getColor() != currentColor)) {
            Coordinate newCoordinate = movement.getDoubledRightUp(reviseCheckerCoordinate);
            if ((newCoordinate != null) && (board.getChecker(newCoordinate) == null)) {
                MovementCalculator.StrokeMove strokeMove = new MovementCalculator.StrokeMove(reviseCheckerCoordinate, checkerToDestroyCoordinate, newCoordinate);
                strokeMoves.add(strokeMove);
            }
        }
        checkerToDestroyCoordinate = movement.getLeftUp(reviseCheckerCoordinate);
        checkerToDestroy = (checkerToDestroyCoordinate != null) ? board.getChecker(checkerToDestroyCoordinate) : null;
        if ((checkerToDestroy != null) && (checkerToDestroy.getColor() != currentColor)) {
            Coordinate newCoordinate = movement.getDoubledLeftUp(reviseCheckerCoordinate);
            if ((newCoordinate != null) && (board.getChecker(newCoordinate) == null)) {
                MovementCalculator.StrokeMove strokeMove = new MovementCalculator.StrokeMove(reviseCheckerCoordinate, checkerToDestroyCoordinate, newCoordinate);
                strokeMoves.add(strokeMove);
            }
        }
        checkerToDestroyCoordinate = movement.getLeftDown(reviseCheckerCoordinate);
        checkerToDestroy = (checkerToDestroyCoordinate != null) ? board.getChecker(checkerToDestroyCoordinate) : null;
        if ((checkerToDestroy != null) && (checkerToDestroy.getColor() != currentColor)) {
            Coordinate newCoordinate = movement.getDoubledLeftDown(reviseCheckerCoordinate);
            if ((newCoordinate != null) && (board.getChecker(newCoordinate) == null)) {
                MovementCalculator.StrokeMove strokeMove = new MovementCalculator.StrokeMove(reviseCheckerCoordinate, checkerToDestroyCoordinate, newCoordinate);
                strokeMoves.add(strokeMove);
            }
        }

        checkerToDestroyCoordinate = movement.getRightDown(reviseCheckerCoordinate);
        checkerToDestroy = (checkerToDestroyCoordinate != null) ? board.getChecker(checkerToDestroyCoordinate) : null;
        if ((checkerToDestroy != null) && (checkerToDestroy.getColor() != currentColor)) {
            Coordinate newCoordinate = movement.getDoubledRightDown(reviseCheckerCoordinate);
            if ((newCoordinate != null) && (board.getChecker(newCoordinate) == null)) {
                MovementCalculator.StrokeMove strokeMove = new MovementCalculator.StrokeMove(reviseCheckerCoordinate, checkerToDestroyCoordinate, newCoordinate);
                strokeMoves.add(strokeMove);
            }
        }
        return strokeMoves;
    }

    private void addStrokeMovements() {
        for (int v = 0; v < size; v++) {
            for (int h = 0; h < size; h++) {
                Coordinate coordinate = new Coordinate(h, v);
                Checker revisionChecker = board.getChecker(coordinate);
                if ((revisionChecker != null) && (revisionChecker.getColor() == currentColor)) {
                    if (revisionChecker.isQueen()) {
                        ArrayList<Coordinate> queenLeftUpPossibleMovements = movement.checkLeftUpDiag(coordinate);
                        ArrayList<Coordinate> queenLeftDownPossibleMovements = movement.checkRightDownDiag(coordinate);
                        ArrayList<Coordinate> queenRightUpPossibleMovements = movement.checkRightUpDiag(coordinate);
                        ArrayList<Coordinate> queenRightDownPossibleMovements = movement.checkLeftDownDiag(coordinate);

                        boolean addOthers = false;
                        Coordinate checkerToDestroy = null;

                        for (Coordinate move : queenLeftUpPossibleMovements) {
                            if (addOthers) {
                                strokeMoves.add(new MovementCalculator.StrokeMove(coordinate, checkerToDestroy, move));
                            }
                            if ((getChecker(move) != null) && (getChecker(move).getColor() != currentColor)) {
                                addOthers = true;
                                checkerToDestroy = move;
                            }
                        }

                        addOthers = false;
                        for (Coordinate move : queenLeftDownPossibleMovements) {
                            if (addOthers) {
                                strokeMoves.add(new MovementCalculator.StrokeMove(coordinate, checkerToDestroy, move));
                            }
                            if ((getChecker(move) != null) && (getChecker(move).getColor() != currentColor)) {
                                addOthers = true;
                                checkerToDestroy = move;
                            }
                        }

                        addOthers = false;
                        for (Coordinate move : queenRightUpPossibleMovements) {
                            if (addOthers) {
                                strokeMoves.add(new MovementCalculator.StrokeMove(coordinate, checkerToDestroy, move));
                            }
                            if ((getChecker(move) != null) && (getChecker(move).getColor() != currentColor)) {
                                addOthers = true;
                                checkerToDestroy = move;
                            }
                        }
                        addOthers = false;
                        for (Coordinate move : queenRightDownPossibleMovements) {
                            if (addOthers) {
                                strokeMoves.add(new MovementCalculator.StrokeMove(coordinate, checkerToDestroy, move));
                            }
                            if ((getChecker(move) != null) && (getChecker(move).getColor() != currentColor)) {
                                addOthers = true;
                                checkerToDestroy = move;
                            }
                        }
                    } else {
                        strokeMoves.addAll(getStrokeMovements(coordinate));
                    }
                }
            }
        }
    }


    public int getSize() {
        return board.getSize();
    }

    public Checker getChecker(Coordinate coordinate) {
        return board.getChecker(coordinate);
    }

    public List<MovementCalculator.StrokeMove> getStrokeMoves() {
        return strokeMoves;
    }
}

