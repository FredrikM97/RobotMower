package org.robot.mower;

import org.robot.mower.global.Commands;
import org.robot.mower.objects.RoomVisualizer;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulation {
	private static final Logger logger = LoggerFactory.getLogger(Simulation.class);
	private final Room room;
	private final Robot robot;
	private final RoomVisualizer visualizer;

	public Simulation(Room room, Robot robot, RoomVisualizer visualizer){
		this.room = room;
		this.robot = robot;
		this.visualizer = visualizer;
	}

	public Simulation(Room room, Robot robot){
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
					turnLeft();
					break;
				case R:
					turnRight();
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
			visualizer.updateMap(getRobot().getX(), getRobot().getY(), getRobot().getOrientation());
			visualizer.displayMap();
		}
	}
	private void turnLeft(){
		this.robot.setOrientation(this.robot.getOrientation().previous());
		logger.info("Turn left {} {} {}", this.getRobot().getX(), this.getRobot().getY(), this.robot.getOrientation());

	}
	private void turnRight(){
		this.robot.setOrientation(this.robot.getOrientation().next());
		logger.info("Turn right {} {} {}", this.getRobot().getX(), this.getRobot().getY(), this.robot.getOrientation());

	}
	private void moveForward(){
		switch(this.robot.getOrientation()){
			case N: //Towards min size of room
				if(this.robot.getY() <= 0)
					throw new IllegalArgumentException("Attempt to move the robot North - Outside the hight limit of the room");
				this.robot.setY(this.robot.getY()-1);
				break;
			case E: //Towards max size of room
				if(this.robot.getX() >= this.room.getWidth())
					throw new IllegalArgumentException("Attempt to move the robot East - Outside the width limit of the room");
				this.robot.setX(this.robot.getX()+1);
				break;
			case S: //Towards max size of room
				if(this.robot.getY() >= this.room.getHeight())
					throw new IllegalArgumentException("Attempt to move the robot South - Outside the height limit of the room");
				this.robot.setY(this.robot.getY()+1);
				break;
			case W: //Towards min size of room
				if(this.robot.getX() <= 0)
					throw new IllegalArgumentException("Attempt to move the robot West - Outside the width limit of the room");
				this.robot.setX(this.robot.getX()-1);
				break;
		}
		logger.info("Forward {} {} {}",  this.robot.getX(), this.robot.getY(),this.robot.getOrientation());
	}

	public Room getRoom() {
		return this.room;
	}

	public Robot getRobot() {
		return this.robot;
	}

	public String getReport(){
		return "Report: %s %s %s".formatted(getRobot().getX(), getRobot().getY(), getRobot().getOrientation());
	}

	public boolean hasVisualizer() {
		return this.visualizer != null;
	}
}
