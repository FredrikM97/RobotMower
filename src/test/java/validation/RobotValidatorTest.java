package validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;
import org.robot.mower.validator.RobotValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RobotValidatorTest {

    private Room room;
    private Robot robot;

    @BeforeEach
    public void setUp() {
        room = new Room(5, 5);
    }

    @Test
    public void testValidMovementNorth() {
        robot = new Robot(2, 2, Orientation.N);

        assertDoesNotThrow(() -> RobotValidator.validateRobotMovement(robot, room));
    }

    @Test
    public void testInvalidMovementNorth() {
        robot = new Robot(2, 0, Orientation.N);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RobotValidator.validateRobotMovement(robot, room);
        });
        assertEquals("Attempt to move the robot North - Outside the room boundaries", exception.getMessage());
    }

    @Test
    public void testValidMovementEast() {
        robot = new Robot(3, 2, Orientation.E);

        assertDoesNotThrow(() -> RobotValidator.validateRobotMovement(robot, room));
    }

    @Test
    public void testInvalidMovementEast() {
        robot = new Robot(4, 2, Orientation.E);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RobotValidator.validateRobotMovement(robot, room);
        });
        assertEquals("Attempt to move the robot East - Outside the room boundaries", exception.getMessage());
    }

    @Test
    public void testValidMovementSouth() {
        robot = new Robot(2, 3, Orientation.S);

        assertDoesNotThrow(() -> RobotValidator.validateRobotMovement(robot, room));
    }

    @Test
    public void testInvalidMovementSouth() {
        robot = new Robot(2, 4, Orientation.S);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RobotValidator.validateRobotMovement(robot, room);
        });
        assertEquals("Attempt to move the robot South - Outside the room boundaries", exception.getMessage());
    }

    @Test
    public void testValidMovementWest() {
        robot = new Robot(2, 2, Orientation.W);

        assertDoesNotThrow(() -> RobotValidator.validateRobotMovement(robot, room));
    }

    @Test
    public void testInvalidMovementWest() {
        robot = new Robot(0, 2, Orientation.W);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RobotValidator.validateRobotMovement(robot, room);
        });
        assertEquals("Attempt to move the robot West - Outside the room boundaries", exception.getMessage());
    }

    @Test
    public void testRobotWithinRoomBoundaries() {
        robot = new Robot(2, 2, Orientation.N);

        assertDoesNotThrow(() -> RobotValidator.withinRoomBoundaries(robot, room));
    }

    @Test
    public void testRobotOutsideRoomBoundaries() {
        robot = new Robot(5, 5, Orientation.N);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            RobotValidator.withinRoomBoundaries(robot, room);
        });
        assertEquals("Robot is not allowed to exist outside room boundaries", exception.getMessage());
    }
}
