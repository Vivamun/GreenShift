package sineSection.greenshift.world.generator;

import java.util.HashMap;
import java.util.Map;

import sineSection.greenshift.world.Room;

public class RoomGenerator {
	//holds the generatable rooms, keyed by their biome generation name.
	private final Map<String,Room> rooms;
	
	public RoomGenerator() {
		rooms = new HashMap<>();
	}
	
	public void addRoom(Room room) {
		rooms.put(room.getName(), room);
	}
	
	public Room getNewRoom(String name) {
		return rooms.get(name).copy();
	}
}
