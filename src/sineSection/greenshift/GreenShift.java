package sineSection.greenshift;

import sineSection.greenshift.world.ShipWorld;
import sineSection.util.LogWriter;

/**
 * GREENSHIFT MK2
 * 
 * "We probably should javadoc this time." -Richard
 * 
 * @author William ? (DMWCincy)
 * @author Ketu ? (Richie_TheRich)
 * @author Richard Abbott (Richie_TheRich)
 *
 */
public class GreenShift {

	public static final boolean LAUNCHABLE = false;
	public static boolean TESTING;

	private static GreenShift gameInstance;
	private final ShipWorld world;

	public static void main(String[] args) {
		LogWriter.createLogger(Reference.TITLE);
		
		TESTING = true;
		for (String arg : args) {
			switch(arg) {
			case "gameEnv":
				TESTING = false;
				break;
			}
		}
		// try {
		// System.out.print("INITATING LAUNCH SEQUENCE");
		// Thread.sleep(1000);
		// System.out.print(".");
		// Thread.sleep(1000);
		// System.out.print(".");
		// Thread.sleep(1000);
		// System.out.println(".");
		// Thread.sleep(1000);
		// if (LAUNCHABLE)
		// System.out.println("The S.S. Greenshift has been launched. Have a
		// pleasant trip.");
		// else {
		// System.out.println("ERROR IN LAUNCH! CODE: 0x" +
		// Integer.toHexString(new Random().nextInt()).toUpperCase()
		// + ", SHORT: \"ERR_NYI_LAUNCH\", FULL: \"Launch Simulation
		// Unsucessful. Please implement launch sequence.\"");
		// Thread.sleep(500);
		// System.out.println("ABORTING!");
		// }
		//
		// /*
		// * TO TEST SUPERSERIAL
		// */
		// System.out.println("TESTING LIBS:");
		// System.out.println("\tSUPERSERIAL:");
		// SSDatabase db = new SSDatabase("Test Database");
		// SSObject obj = new SSObject("Test!");
		// obj.addField(SSField.Integer("numMemes", 12));
		// obj.addString(new SSString("bestOS", "linux"));
		// obj.addArray(SSArray.Short("Sharts", new short[] {2, 4, 6, 8}));
		// db.addObject(obj);
		// byte[] dbBytes = new byte[db.getSize()];
		// db.writeBytes(dbBytes, 0);
		// System.out.print("[DATA BEGIN] ");
		// for(byte b : dbBytes) {
		// System.out.print((char)b);
		// }
		// System.out.println(" [DATA END]");
		//
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		gameInstance = new GreenShift();
	}
	
	public static GreenShift getGameInstance() {
		return gameInstance;
	}

	public GreenShift() {
		world = new ShipWorld();
		world.loadTestEnv();
	}

	public ShipWorld getGameWorld() {
		return world;
	}
}
