package global;

import org.junit.jupiter.api.Test;
import org.robot.mower.global.Orientation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {

	@Test
	public void testNext() {
		assertEquals(Orientation.E, Orientation.N.next());
		assertEquals(Orientation.S, Orientation.E.next());
		assertEquals(Orientation.W, Orientation.S.next());
		assertEquals(Orientation.N, Orientation.W.next());
	}

	@Test
	public void testPrevious() {
		assertEquals(Orientation.W, Orientation.N.previous());
		assertEquals(Orientation.N, Orientation.E.previous());
		assertEquals(Orientation.E, Orientation.S.previous());
		assertEquals(Orientation.S, Orientation.W.previous());
	}
}
