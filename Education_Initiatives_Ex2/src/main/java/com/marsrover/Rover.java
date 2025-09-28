package com.marsrover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rover {
    private final String name;
    private Position position;
    private Direction direction;
    private int battery;
    private final List<String> telemetryLog = new ArrayList<>();
    private final Set<Position> collectedSamples = new HashSet<>();

    public Rover(String name, Position position, Direction direction, int battery) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.battery = battery;
        telemetryLog.add("[" + name + "] Initialized at " + position + " facing " + direction);
    }

    public void move(Grid grid) {
        Position nextPos = new Position(position.getX(), position.getY());
        switch (direction) {
            case N -> nextPos.setY(nextPos.getY() + 1);
            case S -> nextPos.setY(nextPos.getY() - 1);
            case E -> nextPos.setX(nextPos.getX() + 1);
            case W -> nextPos.setX(nextPos.getX() - 1);
        }

        if (!grid.isValidPosition(nextPos) || grid.isObstacle(nextPos)) {
            telemetryLog.add("[" + name + "] Cannot move to " + nextPos + " (obstacle or out of bounds)");
            return;
        }

        position = nextPos;
        battery = Math.max(0, battery - 5);
        telemetryLog.add("[" + name + "] Moved to " + position + ". Battery: " + battery + "%");
    }

    public void turnLeft() {
        direction = direction.turnLeft();
        telemetryLog.add("[" + name + "] Turned left. Now facing " + direction);
    }

    public void turnRight() {
        direction = direction.turnRight();
        telemetryLog.add("[" + name + "] Turned right. Now facing " + direction);
    }

    public void collectSample(Grid grid) {
        if (grid.isSamplePosition(position)) {
            collectedSamples.add(new Position(position.getX(), position.getY()));
            grid.removeSample(position);
            telemetryLog.add("[" + name + "] Collected sample at " + position);
        } else {
            telemetryLog.add("[" + name + "] No sample at " + position);
        }
    }

    public void charge(int amount) {
        battery = Math.min(100, battery + amount);
        telemetryLog.add("[" + name + "] Charged by " + amount + "%. Battery: " + battery + "%");
    }

    public String getStatus() {
        return name + ": Position=" + position + ", Direction=" + direction + ", Battery=" + battery + "%, Samples=" + collectedSamples.size();
    }

    public Position getPosition() { return position; }
    public Direction getDirection() { return direction; }
    public List<String> getTelemetryLog() { return telemetryLog; }
    public Set<Position> getCollectedSamples() { return collectedSamples; }
    public int getCollectedSamplesCount() { return collectedSamples.size(); }
}
