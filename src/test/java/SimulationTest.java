import org.junit.jupiter.api.Test;
import org.robot.mower.Simulation;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
	@Test
	public void testCommandMoveForward(){
		Room room = Room.fromInput("5 5");

		Simulation simulation;
		// Test move forward in North direction
		simulation = new Simulation(room, Robot.fromInput("3 3 N"));
		simulation.useCommand("F");
		assertEquals("Report: 3 2 N", simulation.getReport());

		// Test move forward in East direction
		simulation = new Simulation(room, Robot.fromInput("3 3 E"));
		simulation.useCommand("F");
		assertEquals("Report: 4 3 E", simulation.getReport());

		// Test move forward in West direction
		simulation = new Simulation(room, Robot.fromInput("3 3 W"));
		simulation.useCommand("F");
		assertEquals("Report: 2 3 W", simulation.getReport());

		// Test move forward in South direction
		simulation = new Simulation(room, Robot.fromInput("3 3 S"));
		simulation.useCommand("F");
		assertEquals("Report: 3 4 S", simulation.getReport());
	}

	@Test
	public void testUseCommandRightForward() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("1 2 N");
		Simulation simulation = new Simulation(room, robot);
		simulation.useCommand("RFRFFRFRF");

		assertNotNull(robot, "Robot should not be null.");
		assertEquals(1, robot.getX());
		assertEquals(3, robot.getY());
		assertEquals(Orientation.N, robot.getOrientation());
	}

	@Test
	public void testUseCommandLeftRightForward() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("0 0 E");
		Simulation simulation = new Simulation(room, robot);
		simulation.useCommand("RFLFFLRF");

		assertNotNull(robot, "Robot should not be null.");
		assertEquals(3, robot.getX());
		assertEquals(1, robot.getY());
		assertEquals(Orientation.E, robot.getOrientation());
	}



	@Test
	public void testMoveForwardBeyondNorthRoomBoundaries() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("0 0 N");

		Simulation simulation = new Simulation(room, robot);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			simulation.useCommand("F");
		});

		String expectedMessage = "Attempt to move the robot North - Outside the hight limit of the room";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testMoveForwardBeyondSouthRoomBoundaries() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("0 4 S");
		Simulation simulation = new Simulation(room, robot);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			simulation.useCommand("F");
		});

		String expectedMessage = "Attempt to move the robot South - Outside the height limit of the room";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	public void testMoveForwardBeyondWestRoomBoundaries() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("0 0 W");
		Simulation simulation = new Simulation(room, robot);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			simulation.useCommand("F");
		});

		String expectedMessage = "Attempt to move the robot West - Outside the width limit of the room";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testMoveForwardBeyondEastRoomBoundaries() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("5 5 E");
		Simulation simulation = new Simulation(room, robot);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			simulation.useCommand("F");
		});

		String expectedMessage = "Attempt to move the robot East - Outside the width limit of the room";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	public void testTurnLeft() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("1 2 N");
		Simulation simulation = new Simulation(room, robot);

		simulation.useCommand("L");

		assertNotNull(robot);
		assertEquals(Orientation.W, robot.getOrientation());
	}

	@Test
	public void testTurnRight() {
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("1 2 N");
		Simulation simulation = new Simulation(room, robot);

		simulation.useCommand("R");

		assertNotNull(robot);
		assertEquals(Orientation.E, robot.getOrientation());
	}
}
