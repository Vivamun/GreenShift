package greenshift;

import java.util.Random;

import net.keabotstudios.superserial.BinaryWriter;
import net.keabotstudios.superserial.containers.SSArray;
import net.keabotstudios.superserial.containers.SSDatabase;
import net.keabotstudios.superserial.containers.SSField;
import net.keabotstudios.superserial.containers.SSObject;
import net.keabotstudios.superserial.containers.SSString;
/**
 * GREENSHIFT MK2
 * 
 * "We probably should javadoc this time."
 *  -Richard
 *
 */
public class GreenShift {

	public static final boolean LAUNCHABLE = false;

	public static void main(String[] args) {
		try {
			System.out.print("INITATING LAUNCH SEQUENCE");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			if (LAUNCHABLE)
				System.out.println("The S.S. Greenshift has been launched. Have a pleasant trip.");
			else {
				System.out.println("ERROR IN LAUNCH! CODE: 0x" + Integer.toHexString(new Random().nextInt()).toUpperCase()
						+ ", SHORT: \"ERR_NYI_LAUNCH\", FULL: \"Launch Simulation Unsucessful. Please implement launch sequence.\"");
				Thread.sleep(500);
				System.out.println("ABORTING!");
			}
			
			/*
			 * TO TEST SUPERSERIAL
			 */
			System.out.println("TESTING LIBS:");
			System.out.println("\tSUPERSERIAL:");
			SSDatabase db = new SSDatabase("Test Database");
			SSObject obj = new SSObject("Test!");
			obj.addField(SSField.Integer("numMemes", 12));
			obj.addString(new SSString("bestOS", "linux"));
			obj.addArray(SSArray.Short("Sharts", new short[] {2, 4, 6, 8}));
			db.addObject(obj);
			byte[] dbBytes = new byte[db.getSize()];
			db.writeBytes(dbBytes, 0);
			System.out.print("[DATA BEGIN] ");
			for(byte b : dbBytes) {
				System.out.print((char)b);
			}
			System.out.println(" [DATA END]");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
