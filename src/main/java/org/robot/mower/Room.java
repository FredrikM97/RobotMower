package org.robot.mower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Room {
	private static final Logger logger = LoggerFactory.getLogger(Room.class);
	private static final String INPUT_PATTERN = "\\d+ \\d+";

	private int height;
	private int width;


	public Room(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public static Room createRoom(String input) {
		logger.info("Please enter the room dimensions (height and width) separated by a space:");
		if(isValidInput(input)){
			return processValidInput(input);
		}
		throw new IllegalArgumentException("Unexpected input: " + input);
	}
	private static boolean isValidInput(String input) {
		if(!input.matches(INPUT_PATTERN)){
			throw new IllegalArgumentException("Invalid input. Please enter two integers separated by a space. Got: " + input);
		}
		return true;
	}

	private static Room processValidInput(String input){
		String[] parts = input.split(" ");
		int height = Integer.parseInt(parts[0]);
		int width = Integer.parseInt(parts[1]);
		return new Room(height, width);
	}


	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
