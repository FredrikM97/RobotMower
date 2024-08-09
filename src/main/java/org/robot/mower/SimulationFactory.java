package org.robot.mower;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;
import org.robot.mower.ui.RoomVisualizer;

import java.util.Optional;

public class SimulationFactory {

    public static Simulation createSimulation(PropertiesConfiguration config, Room room, Robot robot) {
        Optional<RoomVisualizer> visualizer =
            Optional.ofNullable(config.getBoolean("visualization.enabled") ? new RoomVisualizer(room.getWidth(), room.getHeight()) : null);

        return visualizer.map(v -> new Simulation(room, robot, v))
                         .orElse(new Simulation(room, robot));
    }

    public static PropertiesConfiguration loadConfig() throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("application.yml");
        return config;
    }
}