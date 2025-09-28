package com.marsrover;

import java.util.Set;

public class Mission {
    private final String name;
    private final Set<Position> sampleLocations;

    public Mission(String name, Set<Position> sampleLocations) {
        this.name = name;
        this.sampleLocations = sampleLocations;
    }

    public boolean isMissionComplete(Rover rover) {
        return rover.getCollectedSamples().containsAll(sampleLocations);
    }

    public String getName() { return name; }
}
