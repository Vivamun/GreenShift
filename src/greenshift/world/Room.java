package greenshift.world;

import java.util.EnumMap;
import java.util.Map;

public class Room {
	private final String name;
	private final String description;
	private final Map<Direction, Doorway> doors;
	
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		doors = new EnumMap<>(Direction.class);
	}
	
	public void addDoorway(Doorway door) {
		if(door.getRoomA().equals(this)) {
			doors.put(door.getDirection(),door);
		} else {
			doors.put(door.getDirection().getOpposite(),door);
		}
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * create a copy of this room's spec, for use in generation.
	 * mutable fields are not changed in the copy.
	 * @return A Room with the same not-mutable fields as this.
	 */
	public Room copy() {
		return new Room(name,description);
	}
}
