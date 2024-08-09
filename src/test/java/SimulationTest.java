import org.junit.jupiter.api.Test;
import org.robot.mower.Simulation;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		//A bit odd name, maybe combination is a better word
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
		//A bit odd name, maybe combination is a better word
		Room room = Room.fromInput("5 5");
		Robot robot = Robot.fromInput("0 0 E");
		Simulation simulation = new Simulation(room, robot);
		simulation.useCommand("RFLFFLRF");

		assertNotNull(robot, "Robot should not be null.");
		assertEquals(3, robot.getX());
		assertEquals(1, robot.getY());
		assertEquals(Orientation.E, robot.getOrientation());
	}
}
