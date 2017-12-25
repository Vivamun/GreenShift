package sineSection.greenshift.world;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoorwayTest {

	private static final Direction TEST_DIR = Direction.FORE;
	
	private Doorway underTest;
	
	@BeforeEach
	void setUp() throws Exception {
		underTest = new Doorway(new Room("",""),Direction.FORE);
	}

	@Test
	void testFore() {
		underTest = new Doorway(new Room("",""),Direction.FORE);
		assertEquals(underTest.getDirection(),Direction.FORE);
	}
	
	@Test
	void testAft() {
		underTest = new Doorway(new Room("",""),Direction.AFT);
		assertEquals(underTest.getDirection(),Direction.AFT);
	}
	
	@Test
	void testPort() {
		underTest = new Doorway(new Room("",""),Direction.PORT);
		assertEquals(underTest.getDirection(),Direction.PORT);
	}
	
	@Test
	void testStarboard() {
		underTest = new Doorway(new Room("",""),Direction.STARBOARD);
		assertEquals(underTest.getDirection(),Direction.STARBOARD);
	}
	
	@Test
	void testUnblocked() {
		assertTrue(underTest.use());
	}
	
	@Test
	void testBlocked() {
		underTest = new Doorway(new Room("",""),Direction.FORE,true);
		assertFalse(underTest.use());
	}

}
