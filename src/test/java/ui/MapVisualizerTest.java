package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.robot.mower.global.Orientation;
import org.robot.mower.ui.RoomVisualizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapVisualizerTest {

    private RoomVisualizer visualizer;

    @BeforeEach
    public void setUp() {
        visualizer = new RoomVisualizer(5, 5);
    }

    @Test
    public void testInitialMap() {
        String expectedMap = """
            .....
            .....
            .....
            .....
            .....
            """;
    assertEquals(expectedMap, captureMapOutput());
    }

    @Test
    public void testRobotOrientationNorth() {
    visualizer.updateMap(1, 2, Orientation.N);
    String expectedMap = """
                .....
                .....
                .^...
                .....
                .....
                """;



    assertEquals(expectedMap, captureMapOutput());
    }

    @Test
    public void testRobotOrientationEast() {
        visualizer.updateMap(4, 3, Orientation.E);
        String expectedMap = """
                    .....
                    .....
                    .....
                    ....>
                    .....
                    """;
        assertEquals(expectedMap, captureMapOutput());
    }

    @Test
    public void testRobotOrientationSouth() {
        visualizer.updateMap(2, 1, Orientation.S);
        String expectedMap = """
                    .....
                    ..v..
                    .....
                    .....
                    .....
                    """;

        assertEquals(expectedMap, captureMapOutput());
    }

    @Test
    public void testRobotOrientationWest() {
        visualizer.updateMap(0, 0, Orientation.W);
        String expectedMap ="""
                    <....
                    .....
                    .....
                    .....
                    .....
                    """;
        assertEquals(expectedMap, captureMapOutput());
    }

    private String captureMapOutput() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : visualizer.getMap()) {
            sb.append(new String(row)).append('\n');
        }
        return sb.toString();
    }
}