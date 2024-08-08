import org.robot.mower.Room;
import org.robot.mower.RoomCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class RoomCreatorIT {
	@Test
	void testCreateRoomWithValidationAfterInvalidInputs() {
		String inputString = String.join("\n", "invalid", "5 test", "5 7");
		InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

		Scanner scanner = new Scanner(inputStream);

		try (MockedStatic<Room> roomMockedStatic = Mockito.mockStatic(Room.class)) {
			roomMockedStatic.when(() -> Room.createRoom(anyString()))
				.thenAnswer(invocation -> {
					String input = invocation.getArgument(0);
					if (input.equals("invalid\n")) {
						throw new IllegalArgumentException();
					} else if (input.equals("5 test\n")) {
						throw new IllegalArgumentException();
					} else if(input.equals("5 7\n")) {
						return new Room(5, 7);
					}
				});

			Room room = RoomCreator.createRoomWithValidation(scanner);
			assertNotNull(room, "Expected a valid room object");
			assertEquals(5, room.getHeight(), "Incorrect room height");
			assertEquals(7, room.getWidth(), "Incorrect room width");
		}
	}
}
