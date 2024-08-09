package org.robot.mower.objects;

import org.robot.mower.global.Orientation;
import org.robot.mower.validator.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Robot {
	private static final Logger logger = LoggerFactory.getLogger(Robot.class);

	private int x;
	private int y;
	private Orientation orientation;

	public Robot(int x, int y, Orientation orientation){
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	public static Robot fromInput(String input) {
		InputValidator.validateRobotInput(input);

		String[] parts = input.split(" ");
		int x = Integer.parseInt(parts[0]);
		int y = Integer.parseInt(parts[1]);
		Orientation orientation = Orientation.valueOf(parts[2]);

		return new Robot(x, y, orientation);
	}

	public void changeX(int x) {
		this.x += x;
	}

	public void changeY(int y) {
		this.y += y;
	}

	public void turnLeft() {
		logger.debug("Turn Left {} {} {}", this.x, this.y, this.getOrientation());
		this.orientation = this.orientation.previous();
	}

	public void turnRight() {
		logger.debug("Turn Right {} {} {}", this.x, this.y, this.getOrientation());
		this.orientation = this.orientation.next();
	}

	public void moveForward(){
		if(this.orientation == Orientation.W || this.orientation == Orientation.E){
			changeX(this.orientation.getAction());

			logger.debug("Forward {} {} {}",  this.x, this.y,this.orientation);
			return;
		}

		if(this.orientation == Orientation.N || this.orientation == Orientation.S){
			changeY(this.orientation.getAction());
			logger.debug("Forward {} {} {}",  this.x, this.y,this.orientation);
			return;
		}
	}
	public Orientation getOrientation() {
		return orientation;
	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}
}
