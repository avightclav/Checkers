package Checkers;

public class Coordinate {
    private int hCoordinate;
    private int vCoordinate;

    public Coordinate(int hCoordinate, int vCordinate) {
        this.hCoordinate = hCoordinate;
        this.vCoordinate = vCordinate;
    }

    public int getHCoordinate() {
        return hCoordinate;
    }

    public int getVCoordinate() {
        return vCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        return hCoordinate == that.hCoordinate && vCoordinate == that.vCoordinate;
    }

    @Override
    public String toString() {
        return "x: " + hCoordinate + "y: " + vCoordinate;
    }

    @Override
    public int hashCode() {
        return (vCoordinate * 13 + hCoordinate * 51);
    }
}
