package com.marsrover;

import java.util.HashSet;
import java.util.Set;

public class MarsRoverSimulator {
    public static void main(String[] args) {
        int gridWidth = 5;
        int gridHeight = 5;

        // Create obstacles and sample positions
        Set<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2, 2));
        Set<Position> samples = new HashSet<>();
        samples.add(new Position(1, 1));
        samples.add(new Position(3, 3));

        // Initialize grid
        Grid grid = new Grid(gridWidth, gridHeight);
        for (Position obs : obstacles) grid.addObstacle(obs);
        for (Position s : samples) grid.addSample(s);

        // Initialize rover
        Rover rover = new Rover("Perseverance", new Position(0, 0), Direction.N, 100);

        System.out.println("=== Starting Rover Mission ===");

        // Sample movement sequence
        rover.move(grid);
        rover.turnRight();
        rover.move(grid);
        rover.collectSample(grid); // collect sample if present
        rover.charge(20);
        rover.turnLeft();
        rover.move(grid);

        System.out.println("\n=== Mission Status ===");
        System.out.println(rover.getStatus());

        System.out.println("\n=== Telemetry Log ===");
        rover.getTelemetryLog().forEach(System.out::println);
    }
}
