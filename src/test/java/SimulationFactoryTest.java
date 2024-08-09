import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.robot.mower.Simulation;
import org.robot.mower.SimulationFactory;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimulationFactoryTest {

    @Test
    public void testCreateSimulationWithVisualizationEnabled() {
        // Arrange
        PropertiesConfiguration mockConfig = mock(PropertiesConfiguration.class);
        Room mockRoom = new Room(5, 5);
        Robot mockRobot = new Robot(2, 3, Orientation.N);

        when(mockConfig.getBoolean("visualization.enabled")).thenReturn(true);

        Simulation simulation = SimulationFactory.createSimulation(mockConfig, mockRoom, mockRobot);

        assertNotNull(simulation, "Simulation should not be null");
        assertTrue(simulation.hasVisualizer(), "Expected Simulation to have a RoomVisualizer");

    }

    @Test
    public void testCreateSimulationWithVisualizationDisabled() {
        PropertiesConfiguration mockConfig = mock(PropertiesConfiguration.class);
        Room mockRoom = new Room(5, 5);
        Robot mockRobot = new Robot(2, 3, Orientation.N);

        when(mockConfig.getBoolean("visualization.enabled")).thenReturn(false);

        Simulation simulation = SimulationFactory.createSimulation(mockConfig, mockRoom, mockRobot);

        assertNotNull(simulation, "Simulation should not be null");
        assertFalse(simulation.hasVisualizer(), "Expected Simulation to not have RoomVisualizer");

    }

    @Test
    public void testLoadConfigSuccess() throws ConfigurationException {
        PropertiesConfiguration mockConfig = new PropertiesConfiguration();
        mockConfig.setProperty("visualization.enabled", true);

        try (MockedStatic<SimulationFactory> mockedStatic = Mockito.mockStatic(SimulationFactory.class)) {
            mockedStatic.when(SimulationFactory::loadConfig).thenReturn(mockConfig);

            PropertiesConfiguration loadedConfig = SimulationFactory.loadConfig();

            assertNotNull(loadedConfig, "Configuration should not be null");
            assertTrue(loadedConfig.getBoolean("visualization.enabled"), "Visualization should be enabled");
        }
    }

    @Test
    public void testLoadConfigFailure() {

        try (MockedStatic<SimulationFactory> mockedStatic = Mockito.mockStatic(SimulationFactory.class)) {
            mockedStatic.when(SimulationFactory::loadConfig).thenThrow(new ConfigurationException("File not found"));

            assertThrows(ConfigurationException.class, SimulationFactory::loadConfig,
                "Expected ConfigurationException to be thrown");
        }
    }
}