package greenshift.world;

import java.util.HashMap;
import java.util.Map;

import greenshift.world.generator.MapGenerator;
import greenshift.world.generator.RoomGenerator;

public class ShipWorld {
	private static final int DEFAULT_SEED = -1;
	
	private final MapGenerator gen;
	private final RoomGenerator roomGen;
	private final Map<Pos,Room> rooms;
	
	public ShipWorld() {
		gen = new MapGenerator(DEFAULT_SEED);
		roomGen = new RoomGenerator();
		rooms = new HashMap<>();
	}

	public void addRoom(int x, int z) {
		String biome = gen.getBiomeGen().getBiomeAt(x, z);
		rooms.put(new Pos(x,z), roomGen.getNewRoom(biome));
	}
	
	/**
	 * a function to load a preliminiary map for generation testing.
	 * at this stage, this is a wholly-connected, pre-set 3x3 environment to test movement.
	 */
	public void loadTestEnv() {
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j< 3; j++) {
				rooms.put(new Pos(i,j), new Room("testRoom","Room at " + i + "," + j));
			}
		}
	}
	
}
