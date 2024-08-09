package objects;

import org.junit.jupiter.api.Test;
import org.robot.mower.global.Orientation;
import org.robot.mower.objects.Robot;
import org.robot.mower.objects.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {

	@Test
	public void testCreateRoomValidInput() {
		String input = "5 5";
		Room room = Room.fromInput(input);

		assertNotNull(room, "Room should be created.");
		assertEquals(5, room.getHeight());
		assertEquals(5, room.getWidth());
	}

	@Test
	public void testCreateRoomInvalidInput() {
		String input = "5 five";

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Room room = Room.fromInput(input);
		});

		String expectedMessage = "Invalid input. Please enter two integers (height, width) separated by a space.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
