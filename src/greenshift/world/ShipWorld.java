package greenshift.world;

import java.util.HashMap;
import java.util.Map;

import greenshift.world.generator.MapGenerator;

public class ShipWorld {
	private static final int DEFAULT_SEED = -1;
	
	private final MapGenerator gen;
	private final Map<Pos,Room> rooms;
	
	public ShipWorld() {
		gen = new MapGenerator(DEFAULT_SEED);
		rooms = new HashMap<>();
	}

	public Room getRoom(int x, int z) {
		//TODO: implement get room
		/*
		 * If the room exists, return it.
		 * If not, then generate it and return it
		 */
		
		/**
		 * The position of the room that's being queried
		 */
		
		Pos queryPos = new Pos(x,z);
		if (rooms.get(queryPos) == null){
			//Room needs to be generated
			rooms.put(queryPos, generateRoom(queryPos));
		}
		return rooms.get(queryPos);
	}
	
	private Room generateRoom(Pos pos) {
		int xPos = pos.xPos;
		int zPos = pos.zPos;
		
		return null;
	}
	//Method above may render this one obsolete, keeping it here in case it has a use somewhere I don't see
	public void addRoom(int x, int z) {
		String biome = gen.getBiomeGen().getBiomeAt(x, z);
		Pos pos = new Pos(x,z);
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
