package sineSection.greenshift.world.generator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sineSection.greenshift.ApplicationData;
import sineSection.greenshift.launcher.parser.RoomParser;
import sineSection.greenshift.world.Room;

public class RoomGenerator {
	// holds the generatable rooms, keyed by their biome generation name.
	private final Map<String, Room> rooms;
	private final RoomParser parser;

	public RoomGenerator(List<String> biomes) {
		rooms = new HashMap<>();
		parser = new RoomParser();
		for(String biome : biomes) {
			addRoom(parser.parse(ApplicationData.readFile("rooms" + File.separator + biome + ".txt")));
		}
	}

	public void addRoom(Room room) {
		rooms.put(room.getName(), room);
	}

	public Room getNewRoom(String name) {
		return rooms.get(name).copy();
	}
}
