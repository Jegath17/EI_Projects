package com.marsrover;

public class Position {
    private int x, y;

    public Position(int x, int y) { this.x = x; this.y = y; }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position pos = (Position) o;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() { return 31 * x + y; }

    @Override
    public String toString() { return "(" + x + "," + y + ")"; }
}
