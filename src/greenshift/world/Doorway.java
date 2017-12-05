package greenshift.world;

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

}
