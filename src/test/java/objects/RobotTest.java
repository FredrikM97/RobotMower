package objects;

import org.junit.jupiter.api.Test;
import org.robot.mower.Simulation;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotTest {

	@Test
	public void testCreateRobotValidInput() {
		String input = "1 2 N";
		Robot robot = Robot.fromInput(input);

		assertNotNull(robot, "Robot should be created.");
		assertEquals(1, robot.getX());
		assertEquals(2, robot.getY());
		assertEquals(Orientation.N, robot.getOrientation());
	}

	@Test
	public void testCreateRobotInvalidInput() {
		String input = "1 2 North";

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Robot.fromInput(input);
		});

		String expectedMessage = "Invalid input. Please enter (x,y, orientation) separated by a space.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testTurnLeft() {
		Robot robot = Robot.fromInput("1 2 N");
		robot.turnLeft();

		assertNotNull(robot);
		assertEquals(Orientation.W, robot.getOrientation());
	}



	@Test
	public void testTurnRight() {
		Robot robot = Robot.fromInput("1 2 N");
		robot.turnRight();

		assertNotNull(robot);
		assertEquals(Orientation.E, robot.getOrientation());
	}
}
