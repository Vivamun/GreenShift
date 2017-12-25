package sineSection.greenshift.world;

public class Doorway {
	/**
	 * The direction to go from room a to room b. Used later to allow the Room
	 * to simply reference when applying this door, So as to prevent the room
	 * AND this class from having to track the direction. Instead, Query the
	 * Doorway.
	 */
	private final Direction dir;
	private final Room genFrom;
	private boolean blocked;

	public Doorway(Room generatedFrom, Direction dir) {
		this(generatedFrom, dir,false);
	}
	
	public Doorway(Room generatedFrom, Direction dir, boolean blocked) {
		this.genFrom = generatedFrom;
		this.dir = dir;
		this.blocked = blocked;
	}

	public Direction getDirection() {
		return dir;
	}
	
	public Room getGenFrom() {
		return genFrom;
	}

	/**
	 * 
	 * @return true if the player passed through successfully. false if not.
	 */
	public boolean use(/* TODO: args */) {
		// TODO: Return the room the user is NOT in, or Block them.
		if(blocked) {
			return false;
		} else {
			return true;
		}
	}

}
