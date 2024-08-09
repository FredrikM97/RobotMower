package org.robot.mower.objects;

import org.robot.mower.global.Orientation;
import org.robot.mower.validator.InputValidator;

public class Robot {

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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
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
