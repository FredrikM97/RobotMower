package org.robot.mower;

import org.robot.mower.global.Commands;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;
import org.robot.mower.ui.TerminalVisualizer;
import org.robot.mower.validator.RobotValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.robot.mower.validator.RobotValidator.validateRobotMovement;

public class Simulation {
	private static final Logger logger = LoggerFactory.getLogger(Simulation.class);
	private final Room room;
	private final Robot robot;
	private final TerminalVisualizer visualizer;

	public Simulation(Room room, Robot robot, TerminalVisualizer visualizer){
		this.room = room;
		this.robot = robot;
		this.visualizer = visualizer;
	}

	public Simulation(Room room, Robot robot){
		RobotValidator.withinRoomBoundaries(robot, room);
		this.room = room;
		this.robot = robot;
		this.visualizer = null;
	}

	public void useCommand(String input) {
		char[] commands = input.toCharArray();

		displayMap();
		for (char command : commands) {
			switch (Commands.valueOf(String.valueOf(command))) {
				case L:
					getRobot().turnLeft();
					break;
				case R:
					getRobot().turnRight();
					break;
				case F:
					moveForward();
					break;
			}
			displayMap();
		}
	}

	private void displayMap(){
		if(this.visualizer != null) {
			visualizer.update(getRobot());
		}
	}
	private void moveForward(){

		validateRobotMovement(robot,room);
		getRobot().moveForward();
	}

	public Robot getRobot() {
		return this.robot;
	}

	public Room getRoom() {
		return this.room;
	}

	public String getReport(){
		return "Report: %s %s %s".formatted(getRobot().getX(), getRobot().getY(), getRobot().getOrientation());
	}

	public boolean hasVisualizer() {
		return this.visualizer != null;
	}
}
