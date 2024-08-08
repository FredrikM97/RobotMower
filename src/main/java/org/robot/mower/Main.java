package org.robot.mower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		logger.info("Welcome to this Robot Mower application without GUI!");
		Scanner scanner = new Scanner(System.in);
		try {
			RoomCreator.createRoomWithValidation(scanner);
		}catch (IllegalArgumentException e){
			logger.error(e.getMessage());
		}
	}
}