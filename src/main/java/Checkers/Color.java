package Checkers;

public enum Color {
    BLACK, WHITE;

    public Color getOpposite() {
        if (this == BLACK)
            return WHITE;
        else return BLACK;
    }
}
