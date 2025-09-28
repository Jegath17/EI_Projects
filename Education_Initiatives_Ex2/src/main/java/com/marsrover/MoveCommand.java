package com.marsrover;

public class MoveCommand implements Command {
    public void execute(Rover rover, Grid grid) {
        rover.move(grid);
    }
}
