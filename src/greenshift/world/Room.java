package greenshift.world;

import java.util.HashMap;
import java.util.Map;

public class Room {
	private final String name;
	private final String description;
	private final Map<Direction, Doorway> doors;
	
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		doors = new HashMap<>();
	}
	
	public void addDoorway(Doorway door) {
		if(door.getRoomA().equals(this)) {
			doors.put(door.getDirection(),door);
		} else {
//			TODO: implement
		}
	}
}
