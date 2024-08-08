import org.robot.mower.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomCreatorTest {

	@Test
	void testCreateRoomWithValidInput() {
		String input = "5 7";
		Room room = Room.createRoom(input);
		assertNotNull(room);
		assertEquals(5, room.getHeight());
		assertEquals(7, room.getWidth());
	}

	@Test
	void testCreateRoomWithToManyInput() {
		String input = "5 7 42";
		assertThrows(IllegalArgumentException.class, () -> Room.createRoom(input));
	}

	@Test
	void testCreateRoomWithToFewInput() {
		String input = "5";
		assertThrows(IllegalArgumentException.class, () -> Room.createRoom(input));
	}

	@Test
	void testCreateRoomWithNonIntegerInput() {
		String input = "5 abc";
		assertThrows(IllegalArgumentException.class, () -> Room.createRoom(input));
	}
}
