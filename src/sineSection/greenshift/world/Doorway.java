package sineSection.greenshift.world;

import sineSection.greenshift.GreenShift;

public class Doorway {
	private Room a;
	private Room b;
	/**
	 * The direction to go from room a to room b.
	 * Used later to allow the Room to simply reference when applying this door,
	 * So as to prevent the room AND this class from having to track the direction.
	 * Instead, Query the Doorway.
	 */
	private Direction dir;
	
	public Doorway(Room a, Room b, Direction dir) {
		this.a = a;
		this.b = b;
		this.dir = dir;
	}
	
	public Room getRoomA() {
		return a;
	}
	
	public Room getRoomB() {
		return b;
	}
	
	public Direction getDirection() {
		return dir;
	}
	
	public Room use(/*TODO: args*/) {
		//TODO: Return the room the user is NOT in, or Block them.
		return null;
	}

}
