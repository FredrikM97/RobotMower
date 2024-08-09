package org.robot.mower;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;
import org.robot.mower.objects.RoomVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.robot.mower.SimulationFactory.loadConfig;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws IOException, ConfigurationException {
		var config = loadConfig();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		logger.info("Welcome to this Robot Mower application without GUI!");

		logger.info("Please enter the room dimensions (height and width) separated by a space:");
		Room room = Room.fromInput(in.readLine());

		logger.info("Please enter the Robot position and orientation(x, y, orientation) separated by a space:");
		Robot robot = Robot.fromInput(in.readLine());


		Simulation simulation = new Simulation(room, robot, new RoomVisualizer(room.getWidth(), room.getHeight()));

		logger.info("Give commands to your robot!");
		simulation.useCommand(in.readLine());

		logger.info(simulation.getReport());

	}
}