package JavaFX;

import Checkers.Checker;
import Checkers.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class FXChecker extends Circle {

    private Color color;
    private Coordinate coordinate;

    FXChecker(Coordinate coordinate, Checker checker) {
        super(20);
        if (checker.getColor() == Checkers.Color.WHITE)
            this.color = Color.WHITE;
        else this.color = Color.BLACK;
        this.coordinate = coordinate;
        super.setFill(this.color);
    }

    Coordinate getCoordinate() {
        return this.coordinate;
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    void light() {
        this.setStyle("-fx-effect: dropshadow(one-pass-box, yellow, 8, 200, 0, 0);");
    }

    void unlight() {
        this.setStyle("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FXChecker fxChecker = (FXChecker) o;

        return coordinate.equals(fxChecker.coordinate);
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + coordinate.hashCode();
        return result;
    }
}
