package greenshift.world;

import greenshift.world.generator.MapGenerator;

public class ShipWorld {
	private static final int DEFAULT_SEED = -1;
	
	private final MapGenerator gen;
	
	public ShipWorld() {
		gen = new MapGenerator(DEFAULT_SEED);
	}

	public void addRoom(int x, int y) {
		String biome = gen.getBiomeGen().getBiomeAt(x, y);
	}
	
}
