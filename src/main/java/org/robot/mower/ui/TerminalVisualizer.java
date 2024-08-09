package org.robot.mower.ui;

import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;

public class TerminalVisualizer {

    private final int width;
    private final int height;
    private final char[][] grid;

    public TerminalVisualizer(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        clearGrid();
    }

    private void clearGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';  // Empty space
            }
        }
    }

    private void placeRobot(Robot robot) {
        int x = robot.getX();
        int y = robot.getY();
        Orientation orientation = robot.getOrientation();

        char orientationChar;
        switch (orientation) {
            case N:
                orientationChar = '^';
                break;
            case E:
                orientationChar = '>';
                break;
            case S:
                orientationChar = 'v';
                break;
            case W:
                orientationChar = '<';
                break;
            default:
                orientationChar = ' ';
        }

        grid[y][x] = orientationChar;
    }

    public void update(Robot robot) {
        clearGrid();
        placeRobot(robot);
        System.out.print(getGrid());
    }

    public String getGrid() {
        StringBuilder sb = new StringBuilder();
        // first number row
        sb.append("  ");
        for (int x = 0; x < width; x++) {
            sb.append("  ").append(x + 1).append(" ");
        }
        sb.append("\n");

        // Mesh upper first row delimiter
        sb.append("  ");
        for (int x = 0; x < width; x++) {
            sb.append("+---");
        }
        sb.append("+\n");

        // Create all boxes
        for (int y = 0; y < height; y++) {
            // Create vertical | barriers in mesh
            sb.append((y + 1) + " ");
            for (int x = 0; x < width; x++) {
                sb.append("| " + grid[y][x] + " ");
            }
            sb.append("|\n");

            // Add lower border
            sb.append("  ");
	        sb.append("+---".repeat(Math.max(0, width)));
            sb.append("+\n");
        }
        return sb.toString();
    }
}