package Checkers;

public class Checker {
    private Color color;
    private boolean queen = false;

    Checker(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isQueen() {
        return queen;
    }

    void setQueen() {
        this.queen = true;
    }
}
