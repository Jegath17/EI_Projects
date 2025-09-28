package com.marsrover;

import javax.swing.*;
import java.awt.*;

public class MarsRoverGUI {
    private JFrame frame;
    private Rover rover;
    private Grid grid;
    private JTextArea telemetryArea;
    private JLabel statusLabel;
    private GridPanel gridPanel;

    private static final int GRID_SIZE = 5; // 5x5 grid

    public MarsRoverGUI() {
        // Initialize grid
        grid = new Grid(GRID_SIZE, GRID_SIZE);
        grid.addObstacle(new Position(2, 2));
        grid.addSample(new Position(1, 1));
        grid.addSample(new Position(3, 3));

        // Initialize rover
        rover = new Rover("Perseverance", new Position(0, 0), Direction.N, 100);

        // Frame setup
        frame = new JFrame("Mars Rover Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        JButton moveBtn = new JButton("Move");
        JButton leftBtn = new JButton("Turn Left");
        JButton rightBtn = new JButton("Turn Right");
        JButton collectBtn = new JButton("Collect Sample");
        JButton chargeBtn = new JButton("Charge 20%");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(moveBtn);
        buttonPanel.add(leftBtn);
        buttonPanel.add(rightBtn);
        buttonPanel.add(collectBtn);
        buttonPanel.add(chargeBtn);
        buttonPanel.add(exitBtn);

        // Telemetry area
        telemetryArea = new JTextArea(10, 20);
        telemetryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(telemetryArea);

        // Status label
        statusLabel = new JLabel(rover.getStatus());

        // Grid panel
        gridPanel = new GridPanel();

        // Layout
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.EAST);
        frame.add(statusLabel, BorderLayout.SOUTH);

        // Button actions
        moveBtn.addActionListener(e -> {
            rover.move(grid);
            updateStatus();
        });

        leftBtn.addActionListener(e -> {
            rover.turnLeft();
            updateStatus();
        });

        rightBtn.addActionListener(e -> {
            rover.turnRight();
            updateStatus();
        });

        collectBtn.addActionListener(e -> {
            rover.collectSample(grid); // pass grid here
            updateStatus();
        });

        chargeBtn.addActionListener(e -> {
            rover.charge(20);
            updateStatus();
        });

        exitBtn.addActionListener(e -> System.exit(0));

        updateStatus();
        frame.setVisible(true);
    }

    private void updateStatus() {
        telemetryArea.setText(String.join("\n", rover.getTelemetryLog()));
        statusLabel.setText(rover.getStatus());
        gridPanel.repaint();
    }

    // Inner class for the visual grid
    private class GridPanel extends JPanel {
        private static final int CELL_SIZE = 80;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw grid
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(i * CELL_SIZE, (GRID_SIZE - 1 - j) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(i * CELL_SIZE, (GRID_SIZE - 1 - j) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }

            // Draw obstacles
            g.setColor(Color.RED);
            for (Position obs : grid.getObstacles()) {
                int x = obs.getX();
                int y = obs.getY();
                g.fillRect(x * CELL_SIZE + 10, (GRID_SIZE - 1 - y) * CELL_SIZE + 10, CELL_SIZE - 20, CELL_SIZE - 20);
            }

            // Draw samples
            g.setColor(Color.BLUE);
            for (Position s : grid.getSamples()) {
                int x = s.getX();
                int y = s.getY();
                g.fillOval(x * CELL_SIZE + 20, (GRID_SIZE - 1 - y) * CELL_SIZE + 20, CELL_SIZE - 40, CELL_SIZE - 40);
            }

            // Draw rover
            g.setColor(Color.GREEN);
            Position rPos = rover.getPosition();
            g.fillOval(rPos.getX() * CELL_SIZE + 15, (GRID_SIZE - 1 - rPos.getY()) * CELL_SIZE + 15, CELL_SIZE - 30, CELL_SIZE - 30);
            g.setColor(Color.BLACK);
            g.drawString(rover.getDirection().name(), rPos.getX() * CELL_SIZE + CELL_SIZE / 2 - 5, (GRID_SIZE - 1 - rPos.getY()) * CELL_SIZE + CELL_SIZE / 2 + 5);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MarsRoverGUI::new);
    }
}
