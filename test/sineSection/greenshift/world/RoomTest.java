package sineSection.greenshift.world;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest {
	private static final String TEST_NAME = "testRoom";
	private static final String TEST_DESC = "A room for testing things";
	
	private Room underTest;
	
	@BeforeEach
	void setUp() throws Exception {
		underTest = new Room(TEST_NAME, TEST_DESC);
	}

	@Test
	void testName() {
		assertEquals(underTest.getName(),TEST_NAME);
	}
	
	@Test
	void testDescription() {
		assertEquals(underTest.getDescription(),TEST_DESC);
	}
	
	@Test
	void testCopyName() {
		assertEquals(underTest.copy().getName(),TEST_NAME);
	}
	
	@Test
	void testCopyDescription() {
		assertEquals(underTest.copy().getDescription(),TEST_DESC);
	}
}
