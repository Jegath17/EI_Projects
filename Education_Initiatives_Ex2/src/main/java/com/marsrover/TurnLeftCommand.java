package com.marsrover;

public class TurnLeftCommand implements Command {
    public void execute(Rover rover, Grid grid) { rover.turnLeft(); }
}
