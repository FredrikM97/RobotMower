package validation;

import org.junit.jupiter.api.Test;
import org.robot.mower.validator.InputValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputValidatorTest {

	@Test
	public void testValidRobotInput() {
		assertDoesNotThrow(() -> InputValidator.validateRobotInput("2 3 N"));
		assertDoesNotThrow(() -> InputValidator.validateRobotInput("0 0 E"));
		assertDoesNotThrow(() -> InputValidator.validateRobotInput("10 10 S"));
	}

	@Test
	public void testInvalidRobotInput() {
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("2 3"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("2"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("2 3 4"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("2 N"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("N 2 3"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("a b c"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRobotInput("2 3 XX"));
	}

	@Test
	public void testValidRoomInput() {
		assertDoesNotThrow(() -> InputValidator.validateRoomInput("5 5"));
		assertDoesNotThrow(() -> InputValidator.validateRoomInput("10 20"));
		assertDoesNotThrow(() -> InputValidator.validateRoomInput("0 0"));
	}

	@Test
	public void testInvalidRoomInput() {
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRoomInput("5"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRoomInput("5 5 5"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRoomInput("5a 5"));
		assertThrows(IllegalArgumentException.class, () -> InputValidator.validateRoomInput("five five"));
	}

}
