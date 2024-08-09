package org.robot.mower;

import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static Simulation builder;
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		BufferedReader in;
		String input;
		String output = "";

;
		in = new BufferedReader(new InputStreamReader(System.in));
		logger.info("Welcome to this Robot Mower application without GUI!");

		logger.info("Please enter the room dimensions (height and width) separated by a space:");
		input = in.readLine();
		Room room = Room.fromInput(input);

		logger.info("Please enter the Robot position and orientation(x, y, orientation) separated by a space:");
		input = in.readLine();
		Robot robot = Robot.fromInput(input);


		Simulation simulation = new Simulation(room, robot);

		logger.info("Give commands to your robot!");
		input = in.readLine();
		builder.useCommand(input);

		logger.info(builder.getReport());
	}
}