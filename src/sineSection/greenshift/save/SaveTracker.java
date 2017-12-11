package sineSection.greenshift.save;

/**
 * A class to keep track of what must be written to the save file. This includes:
 * <ul>
 * <li>the world seed & Biome File used
 * <li>The order in which room were discovered
 * <li>The player's position & inventory
 * <li>Any items moved by the player from their original location
 * <li>Enemies that have been damaged or killed
 * <li>Mods installed in this save
 * <li>Any other changes made from the pure generated world
 * </ul>
 * 
 * This is to ensure that on load, the world can be regenerated and then any player-made changes
 * can be recreated, to ensure that the game is restored to the same state as it was when
 * the world was last saved and closed
 * 
 * <p>
 * This class will track changes by:
 * <br>be notified whenever there is a change to the world, and update itself as such
 * <br> and/or
 * <br>Check itself against the world state at a determined interval and update to match
 * <br>the first method is likely more suitable for cases such as room discovery, the latter for item movement and player condition
 * 
 * <p>
 * When saving to file, this method can hand off all the data it holds to be written.
 * @author geekman9097
 *
 */
public class SaveTracker {
	
}
