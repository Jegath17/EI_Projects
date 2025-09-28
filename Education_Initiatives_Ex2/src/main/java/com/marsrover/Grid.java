package com.marsrover;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private final int width, height;
    private final Set<Position> obstacles = new HashSet<>();
    private final Set<Position> samples = new HashSet<>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isValidPosition(Position pos) {
        return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < height;
    }

    public boolean isObstacle(Position pos) { return obstacles.contains(pos); }
    public void addObstacle(Position pos) { obstacles.add(pos); }

    public boolean isSamplePosition(Position pos) { return samples.contains(pos); }
    public void addSample(Position pos) { samples.add(pos); }
    public void removeSample(Position pos) { samples.remove(pos); }

    public Set<Position> getObstacles() { return obstacles; }
    public Set<Position> getSamples() { return samples; }
}
