package com.marsrover;

public class TurnRightCommand implements Command {
    public void execute(Rover rover, Grid grid) { rover.turnRight(); }
}
