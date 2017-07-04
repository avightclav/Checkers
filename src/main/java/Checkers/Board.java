package Checkers;


public class Board {
    private int fieldSize;
    private Cell[][] board;

    public int getSize() {
        return fieldSize;
    }

    class Cell {
        boolean isPlaceable;
        Coordinate coordinate;
        Checker checker;

        Cell(int hCoordinate, int vCoordinate, boolean isPlaceable) {
            this.coordinate = new Coordinate(hCoordinate, vCoordinate);
            this.isPlaceable = isPlaceable;
        }

        public boolean isPlaceable() {
            return isPlaceable;
        }
    }

    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        board = new Cell[fieldSize][fieldSize];

        for (int v = fieldSize - 1; v >= 0; v--) {
            for (int h = 0; h < fieldSize; h++) {
                board[v][h] = new Cell(h, v, ((h + v) % 2) == 0);
            }
        }
    }

    public Checker getChecker(Coordinate coordinate) {
        return board[coordinate.getVCoordinate()][coordinate.getHCoordinate()].checker;
    }

    public Cell getCell(Coordinate coordinate) {
        return board[coordinate.getVCoordinate()][coordinate.getHCoordinate()];
    }

    void removeChecker(Coordinate coordinate) {
        board[coordinate.getVCoordinate()][coordinate.getHCoordinate()].checker = null;
    }

    int setCell(Checker checker, Coordinate coordinate) {
        Checker content = board[coordinate.getVCoordinate()][coordinate.getHCoordinate()].checker;
        if (content == null) {
            board[coordinate.getVCoordinate()][coordinate.getHCoordinate()].checker = checker;
            return 1;
        } else return 0;
    }


    int setCell(Coordinate coordinate, Checker checker) {
        return setCell(checker, coordinate);
    }

    public boolean isPlaceable(Coordinate coordinate) {
        return board[coordinate.getVCoordinate()][coordinate.getHCoordinate()].isPlaceable;
    }
}
