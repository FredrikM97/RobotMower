package org.robot.mower.objects;

import org.robot.mower.global.Orientation;

import java.util.Arrays;

public class RoomVisualizer {

    private final int width;
    private final int height;
    private char[][] map;

    public RoomVisualizer(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
        clearMap();
    }

    private void clearMap() {
        for (char[] row : map) {
            Arrays.fill(row, '.');
        }
    }

    public void updateMap(int robotX, int robotY, Orientation orientation) {
        clearMap();
        char robotChar = getRobotSymbol(orientation);
        map[robotY][robotX] = robotChar;
    }

    private char getRobotSymbol(Orientation orientation) {
        switch (orientation) {
            case N:
                return '^';
            case E:
                return '>';
            case S:
                return 'v';
            case W:
                return '<';
            default:
                throw new IllegalArgumentException("The orientation '%s' is not valid".formatted(orientation));
        }
    }

    public void displayMap() {
        for (char[] row : map) {
            System.out.println(new String(row));
        }
    }

    public char[][] getMap() {
        return map;
    }
}