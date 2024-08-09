package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.ui.TerminalVisualizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminalVisualizerTest {

    private TerminalVisualizer visualizer;

    @BeforeEach
    public void setUp() {
        visualizer = new TerminalVisualizer(5, 5);
    }

    @Test
    public void testInitialMap() {
        String expectedMap = """
                1   2   3   4   5\s
              +---+---+---+---+---+
            1 |   |   |   |   |   |
              +---+---+---+---+---+
            2 |   |   |   |   |   |
              +---+---+---+---+---+
            3 |   |   |   |   |   |
              +---+---+---+---+---+
            4 |   |   |   |   |   |
              +---+---+---+---+---+
            5 |   |   |   |   |   |
              +---+---+---+---+---+       
            """;
    assertEquals(expectedMap, visualizer.getGrid());
    }

    @Test
    public void testRobotOrientationNorth() {
    visualizer.update(new Robot(1,2, Orientation.N));
    String expectedMap = """
            1   2   3   4   5\s
          +---+---+---+---+---+
        1 |   |   |   |   |   |
          +---+---+---+---+---+
        2 |   |   |   |   |   |
          +---+---+---+---+---+
        3 |   | ^ |   |   |   |
          +---+---+---+---+---+
        4 |   |   |   |   |   |
          +---+---+---+---+---+
        5 |   |   |   |   |   |
          +---+---+---+---+---+
        """;



    assertEquals(expectedMap, visualizer.getGrid());
    }

    @Test
    public void testRobotOrientationEast() {
        visualizer.update(new Robot(4,3, Orientation.E));
        String expectedMap = """
                1   2   3   4   5\s
              +---+---+---+---+---+
            1 |   |   |   |   |   |
              +---+---+---+---+---+
            2 |   |   |   |   |   |
              +---+---+---+---+---+
            3 |   |   |   |   |   |
              +---+---+---+---+---+
            4 |   |   |   |   | > |
              +---+---+---+---+---+
            5 |   |   |   |   |   |
              +---+---+---+---+---+
              """;
        assertEquals(expectedMap, visualizer.getGrid());
    }

    @Test
    public void testRobotOrientationSouth() {
        visualizer.update(new Robot(2,1, Orientation.S));
        String expectedMap = """
                1   2   3   4   5\s
              +---+---+---+---+---+
            1 |   |   |   |   |   |
              +---+---+---+---+---+
            2 |   |   | v |   |   |
              +---+---+---+---+---+
            3 |   |   |   |   |   |
              +---+---+---+---+---+
            4 |   |   |   |   |   |
              +---+---+---+---+---+
            5 |   |   |   |   |   |
              +---+---+---+---+---+
            """;

        assertEquals(expectedMap, visualizer.getGrid());
    }

    @Test
    public void testRobotOrientationWest() {
        visualizer.update(new Robot(0,0, Orientation.W));
        String expectedMap = """
                1   2   3   4   5\s
              +---+---+---+---+---+
            1 | < |   |   |   |   |
              +---+---+---+---+---+
            2 |   |   |   |   |   |
              +---+---+---+---+---+
            3 |   |   |   |   |   |
              +---+---+---+---+---+
            4 |   |   |   |   |   |
              +---+---+---+---+---+
            5 |   |   |   |   |   |
              +---+---+---+---+---+
            """;
        assertEquals(expectedMap, visualizer.getGrid());
    }
}