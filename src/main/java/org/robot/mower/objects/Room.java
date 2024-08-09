package org.robot.mower.objects;

import org.robot.mower.validator.InputValidator;

public class Room {

	private int width;
	private int height;


	public Room(int width, int height) {
		this.width = width;
		this.height = height;

	}

	public static Room fromInput(String input) {
		InputValidator.validateRoomInput(input);

		String[] parts = input.split(" ");
		int height = Integer.parseInt(parts[0]);
		int width = Integer.parseInt(parts[1]);
		return new Room(width, height);
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}


}
