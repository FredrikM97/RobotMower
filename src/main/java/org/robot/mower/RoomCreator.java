package org.robot.mower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class RoomCreator {;
	private static final Logger logger = LoggerFactory.getLogger(RoomCreator.class);


	public static final int MAX_ATTEMPTS = 3;
	public static Room createRoomWithValidation(Scanner scanner) {
		int attempts = 0;
		while(attempts <= MAX_ATTEMPTS) {

			try{
				return Room.createRoom(scanner.nextLine());
			}catch (IllegalArgumentException e){
				logger.error(e.getMessage());
				attempts++;
			}
		}
		throw new IllegalArgumentException("Failed to create a valid room after maximum attempts.");
	}
}
